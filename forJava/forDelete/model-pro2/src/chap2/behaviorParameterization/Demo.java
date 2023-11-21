package chap2.behaviorParameterization;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年11月13日 18:53
 * 项目名称:  forDelete
 * 文件名称:  Demo
 * 文件描述:  @Description:
 */
public class Demo {
    public static void testFilter(){
        Filter f =x->{
            for (int i = 0; i < x; i++) {
                if (i % 3 == 0) {
                    System.out.print(" " + i);
                }
            }
        };
        f.filter(50);
    }

    public static  void  filter (int x, Condition c){
        for (int i = 0; i < x; i++) {
            if (c.test(i)) {
                System.out.print(" " + i);
            }
        }
        System.out.println();
    }
}
