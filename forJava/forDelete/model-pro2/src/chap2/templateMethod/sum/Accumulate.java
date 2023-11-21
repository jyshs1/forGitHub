package chap2.templateMethod.sum;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年11月20日 18:44
 * 项目名称:  forDelete
 * 文件名称:  Accumulate
 * 文件描述:  @Description:
 */
public class Accumulate {
    public static final double getSum(double a, double b, INext iNext, IItem iItem) {
        double sum = 0;
        for (; a <= b; a = iNext.next(a)) {
            sum += iItem.item(a);
        }
        return sum;
    }

}
