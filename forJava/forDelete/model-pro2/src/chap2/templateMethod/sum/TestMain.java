package chap2.templateMethod.sum;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年11月20日 18:45
 * 项目名称:  forDelete
 * 文件名称:  TestMain
 * 文件描述:  @Description:
 */
public class TestMain {
    public static void main(String[] args) {
/*        IItem item = x -> 1.0 / (x * (x + 2));
        INext next = x -> x + 4;
        double pi = 8 * Accumulate.getSum(1, 10000, next, item);
        System.out.println("pi=" + pi);*/

        double retSum = Accumulate.getSum(1,10,x -> x+1, x -> x+x);
        System.out.println(retSum);
    }
}
