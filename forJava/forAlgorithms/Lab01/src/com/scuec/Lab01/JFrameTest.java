package com.scuec.Lab01;

import java.util.Arrays;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class JFrameTest extends JFrame {
    static final int MOD = (int) (1e9 + 7);
    static int count = 0;
    static int countt1 = 0;//迭代
    static int countt2 = 0;//递归
    static int countt3 = 0;//公式
    static int countt4 = 0;//矩阵

    public void CreateJFrame(String title) {
        JFrame jf = new JFrame(title);
        Container container = jf.getContentPane();
        JButton b = new JButton("开始计算");
        JButton b1 = new JButton("三十秒递归");
        JButton b2 = new JButton("公式F(n)");
        Font f = new Font("宋体", Font.BOLD, 30);
        b.setBounds(10, 50, 200, 75);
        b.setFont(f);
        b.setForeground(new Color(0, 0, 0));
        b.setBackground(new Color(215, 234, 253));
        b.setBorder(BorderFactory.createRaisedBevelBorder());

        b1.setBounds(10, 150, 200, 75);
        b1.setFont(f);
        b1.setForeground(new Color(0, 0, 0));
        b1.setBackground(new Color(215, 234, 253));
        b1.setBorder(BorderFactory.createRaisedBevelBorder());

        b2.setBounds(10, 250, 200, 75);
        b2.setFont(f);
        b2.setForeground(new Color(0, 0, 0));
        b2.setBackground(new Color(215, 234, 253));
        b2.setBorder(BorderFactory.createRaisedBevelBorder());

        JTextArea jt = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(jt);
        scrollPane.setBounds(250, 30, 700, 400);

        JTextArea jt1 = new JTextArea();
        JScrollPane scrollPaneNew = new JScrollPane(jt1);
        scrollPaneNew.setBounds(250, 450, 700, 100);

        JTextArea jt2 = new JTextArea();
        JScrollPane scrollPaneNew1 = new JScrollPane(jt2);
        scrollPaneNew1.setBounds(250, 570, 700, 100);

        JTextArea jt3 = new JTextArea();
        JScrollPane scrollPaneNew2 = new JScrollPane(jt3);
        scrollPaneNew2.setBounds(250, 690, 700, 100);

        jf.setLayout(null);
        container.setBackground(Color.white);
        jf.setVisible(true);
        jf.setSize(1000, 900);
        jf.add(b);
        jf.add(b1);
        jf.add(b2);
        jf.add(scrollPane);
        jf.add(scrollPaneNew);
        jf.add(scrollPaneNew1);
        jf.add(scrollPaneNew2);

        b.addActionListener(new ActionListener() {//最大迭代
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    long prev = 0;
                    long next = 1;
                    int count = 1; // 计数器
                    jt.append(prev + "\n");
                    while (true) {
                        countt1++;
                        long sum = prev + next;
                        if (sum <= 0) { // 检查是否超过了long的最大值
                            jt1.append("利用迭代算法不超过编程环境能够支持的最大整数的斐波那契数是第" + count + "个\n");
                            jt1.append("操作次数：" + String.valueOf(countt1));
                            break;
                        }
                        final long toDisplay = next;
                        SwingUtilities.invokeLater(new Runnable() {

                            public void run() {
                                jt.append(toDisplay + "\n");
                            }
                        });

                        prev = next;
                        next = sum;
                        count++; // 每次迭代时，计数器加一
                    }

                    long startTime = System.currentTimeMillis();
                    try {
                        long fibN = fibonacci(count, startTime);
                        long endTime = System.currentTimeMillis();
                        double timeElapsed = (endTime - startTime) / 1000.0;

                        jt1.append("用时：" + timeElapsed + "秒\n");
                        jt1.append("计算出的最大数是：" + fibN + "\n");
                        jt1.append("操作次数：" + countt2 + "\n");
                    } catch (Exception ex) {
                        jt1.append("不能在一分钟内完成计算2\n");
                        jt1.append("操作次数：" + countt2 + "\n");
                    }
                }).start();
            }
        });

        b1.addActionListener(new ActionListener() {//三十秒
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    int n = 100; // 要计算的斐波那契数列的项数
                    int x = 0;
                    long startTime = System.currentTimeMillis();
                    for (int i = 0; i < n; i++) {
                        double[] sum = new double[92];
                        long result = fib(i);
                        long endTime = System.currentTimeMillis();
                        double timeElapsed = (endTime - startTime) / 1000.0;
                        sum[i] = timeElapsed;
                        /*System.out.println("斐波那契序列元素 " + i + " 是: " + result+"\n");
                        System.out.println("用时：" + timeElapsed + "秒\n");*/
                        if (x == 1) {
                            jt2.append("计算出下一个斐波那契数的时间是" + (sum[i]) + "秒" + "\n");
                            x++;
                        }
                        if (timeElapsed >= 30 && x < 1) {
                            jt2.append("三十秒内能算出的最大斐波那契数是第" + (i - 1) + "个 为" + fib(i - 1) + "\n");
                            x++;
                        }
                        count = 0;
                    }
                }).start();
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//公式
                new Thread(() -> {
                    long prev = 0;
                    long next = 1;
                    int count = 1; // 计数器
                    jt3.append(prev + "\n");
                    while (true) {
                        long sum = prev + next;
                        if (sum <= 0) { // 检查是否超过了long的最大值
                            jt3.append("利用迭代算法不超过编程环境能够支持的最大整数的斐波那契数是第" + count + "个\n");
                            break;
                        }
                        final long toDisplay = next;
                        double phi = (1 + Math.sqrt(5)) / 2;
                        countt3++;
                        int n = 0;
                        long fib1, fib2;
                        fib1 = fibo(count);
                        fib2 = toDisplay;
                        System.out.println(fib1);
                        if (fib1 != fib2 && count != 0) {
                            jt3.append("操作次数" + countt3 + "\n");
                            jt3.append("出现误差时的最小n值为" + count + "\n");
                            break;
                        }

                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                jt3.append(toDisplay + "\n");
                            }
                        });
                        prev = next;
                        next = sum;
                        count++; // 每次迭代时，计数器加一
                    }
                }).start();
            }
        });
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    //---------------------------------------------------------------------------------------
    public static void main(String args[]) {
        new JFrameTest().CreateJFrame("斐波那契数列-计算");
        int n = 10;
        System.out.println("矩阵：" + "\n");
        System.out.println(fib11(n));
        System.out.println("矩阵操作次数：" + countt4);

    }

    public static long fibonacci(int n, long startTime) throws Exception {
        countt2++;
        if (System.currentTimeMillis() - startTime > 60000) { // 检查是否已经超过一分钟
            throw new Exception();
        }
        if (n <= 1)
            return n;
        else
            return fibonacci(n - 1, startTime) + fibonacci(n - 2, startTime);
    }

    public static long fib(int n) {
        count++;
        if (n <= 1) {
            return n;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    public static long fibo(int n) {
        double phi = (1 + Math.sqrt(5)) / 2;
        return Math.round(Math.pow(phi, n) / Math.sqrt(5));
    }

    static long fib11(int N) {
        countt4++;
        long[][] A = new long[][]{{1, 1}, {1, 0}};
        if (N <= 0)
            return 0;
        power(A, N - 1);

        return A[0][0];
    }

    static void multiply(long[][] A, long[][] B) {
        long x = ((A[0][0] * B[0][0]) % MOD + (A[0][1] * B[1][0]) % MOD) % MOD;
        long y = ((A[0][0] * B[0][1]) % MOD + (A[0][1] * B[1][1]) % MOD) % MOD;
        long z = ((A[1][0] * B[0][0]) % MOD + (A[1][1] * B[1][0]) % MOD) % MOD;
        long w = ((A[1][0] * B[0][1]) % MOD + (A[1][1] * B[1][1]) % MOD) % MOD;

        A[0][0] = x;
        A[0][1] = y;
        A[1][0] = z;
        A[1][1] = w;
    }

    static void power(long[][] A, int N) {
        if (N <= 1)
            return;

        power(A, N / 2);
        multiply(A, A);

        if (N % 2 != 0) {
            long[][] M = new long[][]{{1, 1}, {1, 0}};
            multiply(A, M);
        }
    }
}


