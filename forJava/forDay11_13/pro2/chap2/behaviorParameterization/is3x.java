package chap2.behaviorParameterization;


/**
 * 在这里给出对类 is3x 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class is3x implements Condition{
    public boolean test(int n) {
        return (n % 3 == 0);
    }
}
