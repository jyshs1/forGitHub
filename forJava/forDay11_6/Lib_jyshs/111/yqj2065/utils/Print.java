package yqj2065.utils;


/**
 * 在这里给出对类 Print 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Print
{
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
