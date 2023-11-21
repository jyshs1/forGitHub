package chap2.behaviorParameterization;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年11月13日 19:02
 * 项目名称:  forDelete
 * 文件名称:  is3Multiple
 * 文件描述:  @Description: 3的倍数
 */
public class is3x implements Condition{
    public boolean test(int n) {
        return (n % 3 == 0);
    }
}
