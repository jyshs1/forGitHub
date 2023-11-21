package chap2.behaviorParameterization;


/**
 * 在这里给出对类 Main 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Main
{
    public static void main(String[] args) {
                Condition is5X = x -> {
            return (x%5 == 0);
        };
        Demo.filter(999, new is3x().and(is5X.not()));
        
        //Demo.filter(99, is5X);
    }
}
