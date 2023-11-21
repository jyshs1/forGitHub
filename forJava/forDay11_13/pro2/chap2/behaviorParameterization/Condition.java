package chap2.behaviorParameterization;


/**
 * 在这里给出对接口 Condition 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

public interface Condition
{
 public boolean test(int n); 
   default Condition and(Condition other){
        //return (n) -> this.test(n) && other.test(n);
        return new Condition(){
            @Override public boolean test(int n){
                return this.test(n) && other.test(n);
            }
        };
    }
    default Condition or(Condition other){
        return (n) -> this.test(n) || other.test(n);
    }
    default Condition not(){//negate
        return (n) -> !this.test(n);
    }
}
