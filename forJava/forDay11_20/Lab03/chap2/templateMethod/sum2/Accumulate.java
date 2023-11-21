package chap2.templateMethod.sum2;


/**
 * 在这里给出对类 Accumulate 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Accumulate
{
     public static final double getSum(double a, double b, INext iNext, IItem iItem,
                                      double sumInit, ICalculationMethod iCalculationMethod) {
        double sum = sumInit;
        for (; a <= b; a = iNext.next(a)) {
            //sum += iItem.item(a);
            sum = iCalculationMethod.iArithmeticSymbol(sum, iItem.item(a));
            //sum = iArithmeticSymbol(sum, iItem.item(a));
            //sum -> sum += iItem.item(a);
            //x, y -> x * y
            //x, y -> x + y
        }
        return sum;
    }
}
