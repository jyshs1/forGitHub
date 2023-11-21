package chap2.behaviorParameterization;
import java.util.ArrayList;
import java.util.List;

/**
 * 在这里给出对类 IsNarcissistic 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class IsNarcissistic implements Condition
{
     @Override public boolean test(int x){
        if(x<0) throw new IllegalArgumentException();
        if(x<10){ return false; }  //忽略1-9
        else{
            List<Integer> list = new ArrayList();//保存参数x每个位上的数字
            int y =x;
            while (x != 0) {
                list.add( x %10); 
                x = x/10;
            }
            int[] arr =list.stream().mapToInt(Integer::intValue).toArray();
            int e =arr.length;
            int r=0;
            for(int h:arr){
                r +=Math.pow(h,e);
            }
            return y == r;
        }
    }
}
