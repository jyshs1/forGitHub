package yqj2065.utils;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年11月06日 19:12
 * 项目名称:  forAlgorithms
 * 文件名称:  Print
 * 文件描述:  @Description:
 */
public class Print {

    private Print(){}; //以前掉了{}
    public static void pln(Object x){
        System.out.println(x);
    }
    public static void pln(){
        System.out.println();
    }
    /**
     * pln(new char[]{'1', '2', '3'});
     * 没有重载System.out.println(char[])时，输出pln(Object x)
     */
    public static void pln(char[] arr){
        System.out.println( arr);// pln(new char[]{'1', '2', '3'}); 输出123
    }
    /**
     *pln(new int[]{1,2,3,4});
     *输出[1, 2, 3, 4]
     */
    public static void pln(int[] arr){
        System.out.println(java.util.Arrays.toString(arr));
    }
    public static void p(Object x){
        System.out.print(x);
    }
    public static void p(String x){
        System.out.print(x);
    }
}
