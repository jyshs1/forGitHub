import com.scuec.Lab01.FibCompare;
import com.scuec.Lab01.Solution;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.math.BigDecimal;
import java.util.Scanner;

// 按两次 Shift 打开“随处搜索”对话框并输入 `show whitespaces`，
// 然后按 Enter 键。现在，您可以在代码中看到空格字符。
public class Main {
    public static void main(String[] args) throws IOException {

//       Solution solution = new Solution();
//        int n = 10;
//        System.out.println("迭代："+solution.getIteration(n));
//        System.out.println("迭代改进"+ solution.getIterativeImprovement(n));
//        System.out.println("递归：" + solution.getRecursion(n));
//        System.out.println("公式法："+ solution.getFormula(n));
//        System.out.println("矩阵：" + solution.getMatrix(n));

//
//        System.out.println("最大32位"+solution.getMax_32());
//        FibCompare fibc = solution.getMax_32();
//        System.out.println("最大32位："+fibc + "++++"+ fibc.getN());
//        int n1 = fibc.getN();
//        System.out.println("最大32位" + solution.getFibNum_60s(n1));

        Solution solution = new Solution();

        int choice = -1;
        Scanner scanner = new Scanner(System.in);
        int fibN = 0;
        int maxN_32 = 0, maxN_64 = 0;

        while (choice != 0) {
            solution.PrintMenu();
            System.out.print("请输入选择：");
            choice = scanner.nextInt();
            switch (choice) {
                case 0: {
                    System.out.println("欢迎下次使用");
                    System.exit(1);
                    break;
                }
                case 1: {
                    System.out.print("请输入n：");
                    fibN = scanner.nextInt();
                    if (fibN <= 0) {
                        System.out.print("N值大于0！");
                        new BufferedReader(new InputStreamReader(System.in)).readLine();
                        break;
                    }
                    System.out.println("迭代：" + solution.getIteration(fibN));
                    System.out.println("迭代改进" + solution.getIterativeImprovement(fibN));
                    System.out.println("递归：" + solution.getRecursion(fibN));
                    System.out.println("公式法：" + solution.getFormula(fibN));
                    System.out.println("矩阵：" + solution.getMatrix(fibN));
                    new BufferedReader(new InputStreamReader(System.in)).readLine();
                    break;
                }
                case 2: {
                    FibCompare fib_32 = solution.getMaxIter_32();
                    FibCompare fib_64 = solution.getMaxIter_64();
                    System.out.println("迭代最大32位：" + fib_32);
                    System.out.println("迭代最大64位：" + fib_64);
                    maxN_32 = fib_32.getN();
                    maxN_64 = fib_64.getN();
                    new BufferedReader(new InputStreamReader(System.in)).readLine();
                    break;
                }

                case 3: {
                    FibCompare fib_32 = solution.getMaxRecur_32();
                    // FibCompare fib_64 = solution.getMaxRecur_64();
                    System.out.println("递归最大32位：" + fib_32);
                    // System.out.println("递归最大64位：" + fib_64);
                    new BufferedReader(new InputStreamReader(System.in)).readLine();
                    break;
                }
                case 4: {
                    // 如果大于60s
                    FibCompare fib_32 = solution.getMaxIter_32();
                    //FibCompare fib_64 = solution.getMaxIter_64();
                    BigDecimal totalTime32 = solution.getTotalTimeRecur(fib_32.getN());
                   // BigDecimal totalTime64 = solution.getTotalTimeRecur(fib_64.getN());

                    System.out.print("第" + fib_32.getN() + "位斐波那契数字，60s内递归不能完成？ ");
                    if (totalTime32.compareTo(new BigDecimal(60)) == 1) {
                        System.out.print("不能");
                    } else {
                        System.out.print("能");
                    }
                    System.out.println("\n用时：" + totalTime32);

/*                    System.out.print("第" + fib_64.getN() + "位斐波那契数字，60s内递归不能完成？ ");
                    if (totalTime64.compareTo(new BigDecimal(60)) == 1) {
                        System.out.print("不能");
                    } else {
                        System.out.print("能");
                    }
                    System.out.println("  用时：" + totalTime64);*/

                    new BufferedReader(new InputStreamReader(System.in)).readLine();
                    break;
                }

                case 5: {
//                    FibCompare fib_60s = solution.RecursionTime30();
//                    System.out.println(fib_60s);
                    int timeSecond = -1;
                    System.out.print("请输入截至时间：");
                    timeSecond = scanner.nextInt();
                    if (timeSecond < 0) {
                        System.out.println("输入时间错误！");
                        new BufferedReader(new InputStreamReader(System.in)).readLine();
                        break;
                    }
                    BigDecimal minTime = new BigDecimal(timeSecond);
                    FibCompare fib_30s = solution.RecursionTime30(minTime);
                    System.out.println(minTime + "秒内计算出的最大斐波那契数是第" +
                            (fib_30s.getN() - 1) +
                            "\n计算出下一个斐波那契数的时间是：" + fib_30s.getTotalTime());
                    new BufferedReader(new InputStreamReader(System.in)).readLine();
                    break;
                }
                case 6: {
                    FibCompare fib_32 = solution.getMaxIter_32();
                    FibCompare fib_64 = solution.getMaxIter_64();
                    long iterativeNum = 0, formulaNum = 0;
                    int differIndex = -1;
                    for (int i = 3; i < fib_64.getN(); i++) {
                       iterativeNum = solution.getIterativeImprovement(i).getFibNum();
                       formulaNum = solution.getFormula(i).getFibNum();
                       if (iterativeNum != formulaNum) {
                           differIndex = i;
                           new BufferedReader(new InputStreamReader(System.in)).readLine();
                           break;
                       }
                    }
                    if (differIndex != -1) {
                        if (differIndex > fib_32.getN()) {
                            System.out.println("公式法计算在32位下，计算无误差，但在64位下有差别：");
                        }
                        System.out.println("是第" + differIndex + "个数\n"
                        + "公式法计算出来的是：" + formulaNum +"\n迭代法计算出来的是："
                        + iterativeNum);
                    } else {
                        System.out.println("公式法计算在64位下，计算无误差");
                    }
                    new BufferedReader(new InputStreamReader(System.in)).readLine();
                    break;
                }
                default: {
                    System.out.print("输入有误，请从新输入");
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    new BufferedReader(new InputStreamReader(System.in)).readLine();
                    break;
                }
            }
        }

    }
}