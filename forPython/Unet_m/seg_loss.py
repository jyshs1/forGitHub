import torch.nn as nn
import torch


'''
Focal Loss 是一种改进的损失函数，
最初由何恺明团队在论文《Focal Loss for Dense Object Detection》中提出
。它是为了解决在目标检测等任务中常见的类别不平衡问题（如正负样本比例悬殊）以及难易样本不均衡的问题。
'''


#多分类 focalloss
class MultiFocalLoss(nn.Module):
    def __init__(self, alpha=0.25, gamma=2, ignore_id=-1):
        super().__init__()
        self.alpha = alpha
        self.gamma = gamma
        self.ignore_id = ignore_id

    def forward(self, predict, target):
        # 预测的维度转换
        predict = predict.permutue(0, 2, 3, 1).contiguous()
        predict = torch.softmax(predict, dim=-1)
        b, c = predict.size(0),predict.size(3)
        mask = target != self.ignore_id  # size(b,h,w)
        predict = predict[mask].view(-1, c)
        target = target[mask].view(-1)  # predict降维后可能是一维的
        #创建单位矩阵
        one_hot = torch.eye(c, device=predict.device)
        target = one_hot[target].view(-1, c).float()
        #公式部分
        # -alpha*(1-p)^gamma * log(p)
        #alpha作用是给不同分类不同的权重值
        f_loss = (-self.alpha * ((1 - predict) ** self.gamma) * target * torch.log(predict + 1.e-8)).sum(dim=-1) * b
        return f_loss.mean()



class MultiDiceLoss(nn.Module):
    def __init__(self, ignore_id=-1):
        super().__init__()
        self.ignore_id = ignore_id

    def forward(self, predict, target):
        predict = predict.permute(0, 2, 3, 1).contiguous()
        predict = torch.softmax(predict, dim=-1)
        mask = target != self.ignore_id
        c = predict.size(3)

        predict = predict[mask].view(-1, c)
        target = target[mask].view(-1)

        one_hot = torch.eye(c, device=predict.device)
        target = one_hot[target].view(-1,c).float()
        dice_score = ((2 * predict * target) / (predict + target + 1.e-8)).sum(0).mean()
        d_loss = 1.-dice_score
        return d_loss


# 多类别损失函数
class MultiLossFunction(nn.Module):
    def __init__(self, alpha=0.25, gamma=2, ignore_id=-1,
                 type='CE'):  # CrossEntropyLoss 'CE' FocalLoss 'FL' DieceLoss 'DL'
        super().__init__()
        if type == 'CE':
            self.loss_fn = nn.CrossEntropyLoss(ignore_index=ignore_id)
        elif type == 'FL':
            self.loss_fn = MultiFocalLoss(alpha, gamma, ignore_id)
        else:
            self.loss_fn = MultiDiceLoss(ignore_id)

    def forward(self, predict, target):
        return self.loss_fn(predict, target)