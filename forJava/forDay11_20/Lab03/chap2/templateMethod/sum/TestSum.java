package chap2.templateMethod.sum;


/**
 * 在这里给出对类 TestSum 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class TestSum
{
  public static void main(String[] args) {
      /*
        IItem item = x -> 1.0 / (x * (x + 2));
        INext next = x -> x + 4;
        double pi = 8 * Accumulate.getSum(1, 10000, next, item);
        System.out.println("pi=" + pi);
        */
        double retSum = Accumulate.getSum(1,10,x -> x+1, x -> x+x);
        System.out.println(retSum);
    }
}
