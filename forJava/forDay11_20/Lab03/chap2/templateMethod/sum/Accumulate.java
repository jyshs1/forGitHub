package chap2.templateMethod.sum;


/**
 * 在这里给出对类 Accumulate 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Accumulate
{
    public static final double getSum(double a, double b, INext iNext, IItem iItem) {
        double sum = 0;
        for (; a <= b; a = iNext.next(a)) {
            sum += iItem.item(a);
        }
        return sum;
    }
}
