import chap1.temp.IntSort;

/**
 * 在这里给出对类 Main 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Main
{
    public static void main(){
        chap1.temp.IntSort obj = (IntSort) new chap1.temp.BubbleSort();
        chap1.temp.SortTest.setIntSort(obj);
        chap1.temp.SortTest.simpTest(new int[]{5, 8, 5, 4, 3, 5, 2, 7, 5, 9});
    }
}
