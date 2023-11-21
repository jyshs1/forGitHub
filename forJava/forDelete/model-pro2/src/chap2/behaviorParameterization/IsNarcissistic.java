package chap2.behaviorParameterization;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年11月13日 18:50
 * 项目名称:  forDelete
 * 文件名称:  IsNarcissistic
 * 文件描述:  @Description: 44
 */
public class IsNarcissistic implements Condition{
    public boolean test(int x){
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
