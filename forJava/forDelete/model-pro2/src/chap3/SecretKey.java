package chap3;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年11月15日 19:26
 * 项目名称:  forDelete
 * 文件名称:  SecretKey
 * 文件描述:  @Description:
 */
public class SecretKey {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String plainText = "123";
        byte[] bytes = plainText.getBytes(StandardCharsets.UTF_8);
        MessageDigest messageDigest;
        messageDigest = MessageDigest.getInstance("SHA-512");
        //bytes是输入字符串转换得到的字节数组
        messageDigest.update(bytes);
        //转换并返回结果，也是字节数组，包含16个元素
        byte[] digest = messageDigest.digest();
        //字符数组转换成字符串返回
        String result = byteArrayToHexString(digest);
        //转换大写
        System.out.println(result);
    }
    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            //java.lang.Integer.toHexString() 方法的参数是int(32位)类型，
            //如果输入一个byte(8位)类型的数字，这个方法会把这个数字的高24为也看作有效位，就会出现错误
            //如果使用& 0XFF操作，可以把高24位置0以避免这样错误
            String temp = Integer.toHexString(b & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                builder.append("0");
            }
            builder.append(temp);
        }
        return builder.toString();
    }
}
