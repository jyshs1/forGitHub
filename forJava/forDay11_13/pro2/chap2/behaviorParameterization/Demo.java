package chap2.behaviorParameterization;

public class Demo
{

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
