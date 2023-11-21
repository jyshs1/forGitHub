package com.scuec.Lab01;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年10月12日 19:57
 * 项目名称:  forAlgorithms
 * 文件名称:  FibRecursionImpl
 * 文件描述:  @Description: 接口实现
 */
public class FibRecursion {
    public static int count = 0;


    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        FibRecursion.count = count;
    }

    public int getRecursion(int n) {
        count++;
        if (n == 1) {
            return 1;
        } else if (n <= 0) {
            return 0;
        }
        return getRecursion(n-1) + getRecursion(n-2);
    }

}
