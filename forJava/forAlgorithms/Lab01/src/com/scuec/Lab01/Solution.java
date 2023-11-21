package com.scuec.Lab01;

import org.testng.ITestRunnerFactory;
import org.testng.annotations.Test;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.Arrays;

import static java.lang.Math.sqrt;

public class Solution {
    public Solution() {
    }

    public FibCompare getIteration(int n) { //迭代
        BigDecimal beginTime = BigDecimal.valueOf(System.nanoTime());
        if (n <= 0) {
            return null;
        }
        if (n == 1 || n == 2) {
            return new FibCompare(0, 0, 1, n);
        }
        int first = 1, second = 1, third = 0;
        int count = 0;
        for (int i = 2; i < n; i++) {
            third = first + second;
            first = second;
            second = third;
            count++;
        }
        BigDecimal endTime = BigDecimal.valueOf(System.nanoTime());
        BigDecimal totalTime = (endTime.subtract(beginTime)).divide(new BigDecimal(1000000000));

        return new FibCompare<BigDecimal>(count, totalTime, third, n);
    }

    public void PrintMenu() {
        System.out.println( "=====================================================");
        System.out.println("\t\t0、退出");
        System.out.println("\t\t1、五种方法");
        System.out.println("\t\t2、迭代寻找 32、64位编程环境下最大的斐波那契数");
        System.out.println("\t\t3、递归寻找 32、64位编程环境下最大的斐波那契数");
        System.out.println("\t\t4、递归计算 32位编程环境下最大的斐波那契数");
        System.out.println("\t\t5、30秒内计算出的最大斐波");
        System.out.println("\t\t6、公式法什么时候出现误差");
        System.out.println( "=====================================================");
    }


    public FibCompare getIterativeImprovement(int n) {  //迭代改进
        BigDecimal beginTime = BigDecimal.valueOf(System.nanoTime());
        int numN = n;
        if (n <= 0) {
            return null;
        }
        if (n == 1 || n == 2) {
            return new FibCompare(0, 0, 0, n);
        }
        numN--;
        int count = 0;  //总次数
        long prev = numN & 1;
        long next = 1;
        numN >>= 1;
        while (numN-- > 0) {
            prev += next;
            next += prev;
            count +=1;
        }
        BigDecimal endTime = BigDecimal.valueOf(System.nanoTime());
        BigDecimal totalTime = (endTime.subtract(beginTime)).divide(new BigDecimal(1000000000));
        return new FibCompare<BigDecimal>(count, totalTime, next, n);

    }

    public FibCompare getRecursion(int n) { //递归
        BigDecimal beginTime = BigDecimal.valueOf(System.nanoTime());

        if (n <= 0) {
            return null;
        } else if (n == 1 || n == 2) {
            return new FibCompare(0, 0, 1, n);
        }

        this.countRecur = 0;
        int num = (int)recursion(n);
        BigDecimal endTime = BigDecimal.valueOf(System.nanoTime());
        BigDecimal totalTime = (endTime.subtract(beginTime)).divide(new BigDecimal(1000000000));

        return new FibCompare<BigDecimal>(countRecur, totalTime, num, n);
    }

    public FibCompare getFormula(int n) {   //公式法
        BigDecimal beginTime = BigDecimal.valueOf(System.nanoTime());

        if (n <= 0) {
            return null;
        } else if (n == 1 || n == 2) {
            return new FibCompare(0, 0, 1, n);
        }
        int count = 1;


        double z = sqrt(5.0);
        double x = (1 + z) / 2;
        double y = (1 - z) / 2;
        double num = (Math.pow(x, n) - Math.pow(y, n)) / z;
        BigDecimal endTime = BigDecimal.valueOf(System.nanoTime());
        BigDecimal totalTime = (endTime.subtract(beginTime)).divide(new BigDecimal(1000000000));
        long num1 = (long) num;
        return new FibCompare<BigDecimal>(count, totalTime, num1, n);
    }

    @Test
    public FibCompare getMatrix(int n) { //矩阵
        BigDecimal beginTime = BigDecimal.valueOf(System.nanoTime());

        if (n <= 0) {
            return null;
        } else if (n == 1 || n == 2) {
            return new FibCompare(0, 0, 1, n);
        }

        int totalN = n;
        long[][] x = {{1, 1}, {1, 0}};
        long[][] y = {{1, 1}, {1, 0}};
        totalN -= 2;

        this.matrixCount = 0;
        int count = 0;
        while (totalN-- > 0) {
            x = MatrixOperation(x, y);
            count += 8;
        }
        //count =  this.matrixCount;
        BigDecimal endTime = BigDecimal.valueOf(System.nanoTime());
        BigDecimal totalTime = (endTime.subtract(beginTime)).divide(new BigDecimal(1000000000));

        return new FibCompare<BigDecimal>(count, totalTime, x[0][0], n);
    }

    //迭代最大32位
    public FibCompare getMaxIter_32() {
        BigDecimal beginTime = BigDecimal.valueOf(System.nanoTime());

        int first = 1, second = 1, third = 2;
        int count = 3;  //溢出为负
        while (third > 0) {
            first = second;
            second = third;
            third = first + second;
            count++;
        }
        BigDecimal endTime = BigDecimal.valueOf(System.nanoTime());
        BigDecimal totalTime = (endTime.subtract(beginTime)).divide(new BigDecimal(1000000000));
        return new FibCompare<BigDecimal>(count-2, totalTime, second, count-1);
    }
    public FibCompare getMaxIter_64() {
        BigDecimal beginTime = BigDecimal.valueOf(System.nanoTime());

        long first = 1, second = 1, third = 2;
        int count = 3;  //溢出为负
        while (third > 0) {
            first = second;
            second = third;
            third = first + second;
            count++;
        }

        BigDecimal endTime = BigDecimal.valueOf(System.nanoTime());
        BigDecimal totalTime = (endTime.subtract(beginTime)).divide(new BigDecimal(1000000000));
        return new FibCompare<BigDecimal>(count-2, totalTime, second, count-1);
    }
    //递归最大32位
    public FibCompare getMaxRecur_32() {
        BigDecimal beginTime = BigDecimal.valueOf(System.nanoTime());

        int prev = 1;
        int cur = 1;
        this.countRecur = 0;
        int n = 3;
        while (true) {
            prev = cur;
            cur =  (int)recursion(n);;
            if (cur < 0) {
                break;
            }
            System.out.println(n);
            n++;
        }
        int count = 0;  //总次数

        BigDecimal endTime = BigDecimal.valueOf(System.nanoTime());
        BigDecimal totalTime = (endTime.subtract(beginTime)).divide(new BigDecimal(1000000000));
        return new FibCompare<BigDecimal>(this.countRecur, totalTime, prev, n);
    }
//递归最大64位
    public FibCompare getMaxRecur_64() {
        BigDecimal beginTime = BigDecimal.valueOf(System.nanoTime());
        long prev = 1;
        long cur = 1;
        int n = 3;
        this.countRecur = 0;
        while (true) {
            prev = cur;
            cur =  recursion(n);
            if (cur < 0) {
                break;
            }
            System.out.println(n);
            n++;
        }
        int count = 0;  //总次数
        BigDecimal endTime = BigDecimal.valueOf(System.nanoTime());
        BigDecimal totalTime = (endTime.subtract(beginTime)).divide(new BigDecimal(1000000000));
        return new FibCompare<BigDecimal>(this.countRecur, totalTime, prev, n-1);
    }

//递归60s内能不能完成
    public BigDecimal getTotalTimeRecur(int n) {

        BigDecimal beginTime = BigDecimal.valueOf(System.nanoTime());

        long fibNum =  recursion(n);

        BigDecimal endTime = BigDecimal.valueOf(System.nanoTime());
        BigDecimal totalTime = (endTime.subtract(beginTime)).divide(new BigDecimal(1000000000));

        System.out.println("用时totalTime = " + totalTime + "秒");
        return totalTime;

    }

    public FibCompare RecursionTime30(BigDecimal minBigDecimal){
        long prev = 1;
        long cur = 1;
        BigDecimal beginTime = BigDecimal.valueOf(System.nanoTime());

        BigDecimal divTime = new BigDecimal(1000000000);
        BigDecimal endTime = null;
        BigDecimal subTime = null;
        this.countRecur = 0;
        int n = 1;
        while (true) {
            prev = cur;
            cur =  recursion(n);
            endTime = BigDecimal.valueOf(System.nanoTime());
            subTime = (endTime.subtract(beginTime)).divide(divTime);          //时间差值
            //System.out.println(n);
            n++;
            if (subTime.compareTo(minBigDecimal) == 1) {
                break;
            }
        }
        return new FibCompare<BigDecimal>(this.countRecur, subTime, cur, n);
    }


    private int matrixCount = 0;
        public long[][] MatrixOperation(long[][] a, long[][] b) {
        long[][] temp = {{0, 0}, {0, 0}};
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                for (int k = 0; k < temp.length; k++) {
                    temp[i][j] += a[i][k] * b[k][j];
                    matrixCount++;
                }
            }
        }
        return temp;
    }

    private long countRecur;
    private long recursion(int n) {
        countRecur++;
        if (n == 1 || n == 2) {
            return 1;                                       //保证前两个数为1
        }
        if (n > 2) {
            return recursion(n - 1) + recursion(n - 2);     //递归调用
        }
        return -1;
    }


}


//   public  int getMatrix(int n) {
// }
