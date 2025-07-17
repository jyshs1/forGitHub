import os.path

import albumentations as A
import cv2
import numpy as np
import matplotlib.pyplot as plt
import torch

from matplotlib import cm
from matplotlib.colors import Normalize
import torchvision.transforms as transforms
from PIL import Image


def get_transform(is_train=True, image_size=(320, 256)):
    if is_train:
        transform = A.Compose([
            A.LongestMaxSize(max_size=max(image_size) + 16),  # 图像缩放保持原图纵横比
            A.PadIfNeeded(image_size[0], image_size[1] + 16,
                          border_mode=cv2.BORDER_CONSTANT, value=0, mask_value=0),  # 图像填充
            A.RandomResizedCrop(image_size),
            A.ShiftScaleRotate(shift_limit=0.1, scale_limit=0.1, rotate_limit=20, p=0.5),  # 平移缩放旋转
            A.OneOf(  # 随机选择一个增强模块
                [
                    A.MotionBlur(p=0.2),  # 随机大小的核用于模糊图片
                    A.RandomGamma(gamma_limit=(60, 120), p=0.2),  # 随机gamma变换
                    A.RandomBrightnessContrast(brightness_limit=0.2, p=0.6),  # 对比度和亮度随机
                    A.CLAHE(clip_limit=4, tile_grid_size=(4, 4), p=0.5)  # 自适应直方图均衡化
                ], p=1.
            ),
            A.HorizontalFlip(p=0.5)  # 随机旋转
        ])
    else:
        transform = A.Compose([
            A.LongestMaxSize(max_size=max(image_size) + 16),  # 图像缩放保持原图纵横比
            A.PadIfNeeded(image_size[0], image_size[1] + 16,
                          border_mode=cv2.BORDER_CONSTANT, value=0, mask_value=0),  # 图像填充
            A.Resize(image_size[0], image_size[1])  # 图像较小的话Resize
        ])
    return transform


# 原图、掩码、前景抠图显示函数
def images_show(images, masks):
    images = images.cpu().permute(0, 2, 3, 1).contiguous().numpy()
    masks = masks.cpu().numpy() * 255
    mean = np.array([0.485, 0.456, 0.406]).reshape(-1, 1, 1, 3)
    std = np.array([0.229, 0.224, 0.225]).reshape(-1, 1, 1, 3)
    images = ((images * std + mean) * 255).astype(np.uint8)
    n_row = images.shape[0]
    fig, axes = plt.subplots(n_row, 3)

    for i, img in enumerate(images):  # 需要索引值时候引入enumerate
        mask = masks[i]
        aoi_mask = mask > 0
        aoi_image = np.zeros_like(img).astype(np.uint8)
        aoi_image[aoi_mask] = img[aoi_mask]
        if n_row == 1:
            axes[0].imshow(img)
            axes[1].imshow(mask)
            axes[2].imshow(aoi_image)
        else:
            axes[i, 0].imshow(img)
            axes[i, 1].imshow(mask)
            axes[i, 2].imshow(aoi_image)

    plt.show()


# 计算、pa、map、miou
def val_score(predict_masks, true_masks, C=2):
    # 通道数放在最后一维
    with torch.no_grad():
        predict_masks = predict_masks.permute(0, 2, 3, 1).contiguous().cpu()
        predict_masks = torch.softmax(predict_masks, dim=-1)
        predict_masks = torch.argmax(predict_masks, dim=-1)
        predict_masks = torch.flatten(predict_masks)
        true_masks = torch.flatten(true_masks).cpu()
        true_label = true_masks.unique()
        class_id = true_masks * C + predict_masks

        ids, value = torch.unique(class_id, return_counts=True)
        # 混淆矩阵
        matrix = torch.flatten(torch.zeros(C, C))
        for i, id in enumerate(ids):
            matrix[id] = value[i]
        matrix = matrix.view(-1, C)
        pa = torch.diag(matrix).sum() / matrix.sum()
        map = 0.
        miou = 0.
        for i in range(C):
            mpa = map + matrix[i, i] / max(matrix[i].sum(), 1)
            miou = miou + matrix[i, i] / max((matrix[i].sum() + matrix[:i].sum() - matrix[i, i]), 1)
        mpa /= max(true_label.size(0), 1)
        miou /= max(true_label.size(0), 1)

        return pa, mpa, miou, matrix


# 混淆矩阵可视化
def show_confusion_matrix(matrix_np, class_name):
    fig, axes = plt.subplots(figsize=(10, 10))
    # 绘制网络
    axes.imshow(matrix_np, cmap=cm.Blues)
    # 设置x,y轴标签

    axes.set_xticks(np.arange(matrix_np.shape[1]))
    axes.set_yticks(np.arange(matrix_np.shape[0]))

    # x,y轴标签名称
    axes.set_xticklabels(class_name, rotation=45)
    axes.set_yticklabels(class_name)

    # 网格填充数
    for i in range(matrix_np.shape[0]):
        for j in range(matrix_np.shape[1]):
            axes.text(x=j, y=i, s=f'{matrix_np[i, j] * 100:.3f}%', va='center', hia='center', color='red')

    axes.set_title('Confusion Matrix')

    # 颜色映射按照混淆矩阵最值
    normalize = Normalize(vmin=matrix_np.min(), vmax=matrix_np.max())
    # 可视化
    plt.colorbar(axes.imshow(matrix_np, cmap=cm.Blues, norm=normalize))
    plt.tight_layout()
    fig.savefig(r'val_masks\confusion_matrix.png')
    plt.show()


# 图像可视化以及保存前景图片
def save_roi_images(net, images_name_list, image_size=(320, 256), is_show=False):
    device = 'cuda' if torch.cuda.is_available() else 'cpu'
    tf = transforms.Compose(
        [
            transforms.ToTensor(),
            transforms.Normalize((0.485, 0.456, 0.406), (0.229, 0.224, 0.225))
        ]
    )

    for n, img_name in enumerate(images_name_list):
        # #只测试11张原图抠图效果
        # if n>10:
        #     break
        image_pil = Image.open(img_name).convert('RGB')
        image_up = np.array(image_pil)
        albument = get_transform(False, image_size=image_size)(image=image_up)

        image_up = albument['image']
        image_tensor = tf(image_up).unsqueeze(0)
        predict_mask = net(image_tensor.to(device))

        # 归一化
        predict_mask = torch.softmax(predict_mask, dim=-1)
        predict_mask = predict_mask.argmax(dim=1).cpu()

        # 可视化
        if is_show:
            images_show(image_tensor, predict_mask)
        # 前景图片
        roi_image = np.zeros_like(image_up).astype(np.uint8)
        roi_area = predict_mask.squeeze(0).numpy() > 0
        roi_image[roi_area] = image_up[roi_area]
        roi_image = Image.fromarray(roi_image)
        roi_image.save(os.path.join(r'predict'), os.path.basename((img_name)))


# 视频流的视频帧，抠图前景图片拼接显示


def video_concatenate_show(net, video_name, image_size=(320, 256), color_list=[[0, 0, 0], [255, 255, 255]],
                           is_roi_or_mask=True):
    device = 'cuda' if torch.cuda.is_available() else 'cpu'
    tf = transforms.Compose(
        [
            transforms.ToTensor(),
            transforms.Normalize((0.485, 0.456, 0.406), (0.229, 0.224, 0.225))
        ]
    )
    mean = torch.tensor([0.485, 0.456, 0.406], device=device).reshape(1, 1, 3)
    std = torch.tensor([0.229, 0.224, 0.225], device=device).reshape(1, 1, 3)

    cap = cv2.VideoCapture(video_name)
    while True:
        ret, frame = cap.read()

        if ret:
            h, w = frame.shape[0], frame.shape[1]
            albument = get_transform(False, image_size=image_size)(image=frame)
            frame = albument['image']
            frame = tf(frame).unsqueeze(0).to(device)
            predict = net(frame)

            # 获取预测掩膜布尔张量
            mask = torch.softmax(predict, dim=1).argmax(dim=1).squeeze(0)
            frame = (frame.squeeze(0).permute(1, 2, 0).contiguous() * std + mean) * 255

            roi_frame = torch.zeros_like(frame, device=device)

            if is_roi_or_mask:
                mask = mask.type(torch.bool)
                roi_frame[mask] = frame[mask]
            else:
                for lable_id in mask.unique():
                    roi_frame[mask==lable_id] = torch.tensor(color_list[lable_id], device=device).float()
            frame = frame.cpu().numpy().astype(np.np.uint8)
            roi_frame = roi_frame.type(torch.np.uint8).cpu().numpy()
            # 将预测前景帧图片尺寸调整到原视频尺寸大小
            roi_frame = cv2.resize(roi_frame, (w, h), interpolation=0)
            frame = cv2.resize(frame, (w, h), interpolation=0)
            # 将原视频帧和预测前景帧拼接
            cv2.imshow('image', np.concatenate((frame, roi_frame), axis=1))
        if cv2.waitKey(5) & 0xff == ord('q'):
            break
    cap.release()
    cv2.destroyAllWindows()
