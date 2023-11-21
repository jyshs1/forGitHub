package chap2.templateMethod.sum2;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年11月20日 18:44
 * 项目名称:  forDelete
 * 文件名称:  Accumulate
 * 文件描述:  @Description:
 */
public class Accumulate {
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
