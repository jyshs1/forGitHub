package chap1.temp;
import static yqj2065.utils.Print.pln;
/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年11月06日 19:10
 * 项目名称:  forAlgorithms
 * 文件名称:  SortTest
 * 文件描述:  @Description:
 */
public class SortTest {
    private static IntSort ssssssss;
    public static void setIntSort(IntSort ssssssss) {
        SortTest.ssssssss = ssssssss;
    }

    public static void  simpTest (@org.jetbrains.annotations.NotNull IntSort s, int[] array){ // IntSort s作为参数
        array = s.sort(array);
        pln(array);
    }
    //或者， IntSort s作为成员变量，等待setter参数传入，或者依赖注入


    public static void  simpTest (int[] array){
        array = ssssssss.sort(array);
        pln(array);
    }
}
