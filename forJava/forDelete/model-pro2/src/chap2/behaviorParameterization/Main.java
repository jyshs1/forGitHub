package chap2.behaviorParameterization;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年11月13日 18:41
 * 项目名称:  forDelete
 * 文件名称:  Main
 * 文件描述:  @Description:
 */
public class Main {
    public static void main(String[] args) {
        //Demo.testFilter();
      //  Demo.filter(99, new is3x().and(n -> {return (n % 5) == 0;}));
        Condition is5X = x -> (x%5 == 0);
       // Condition is7X = n ->{return (n%7 == 0);};
        Demo.filter(999, new is3x().and(is5X));
        //Demo.filter(99, is5X);

    }
}
