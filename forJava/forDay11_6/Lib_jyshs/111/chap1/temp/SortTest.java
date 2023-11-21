package chap1.temp;
import static yqj2065.utils.Print.pln;

/**
 * 在这里给出对类 SortTest 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class SortTest
{
  public static void  simpTest (IntSort s, int[] array){ // IntSort s作为参数
        array = s.sort(array);
        pln(array);
    } 
    //或者， IntSort s作为成员变量，等待setter参数传入，或者依赖注入
    private static IntSort s;    
    public static  void setIntSort(IntSort s){
        SortTest.s =s;
    }
    public static void  simpTest (int[] array){
        array = s.sort(array);
        pln(array);
    } 
}
