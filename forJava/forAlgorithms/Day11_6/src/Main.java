import chap1.temp.IntSort;

// 按两次 Shift 打开“随处搜索”对话框并输入 `show whitespaces`，
// 然后按 Enter 键。现在，您可以在代码中看到空格字符。
public class Main {
    public static void main(String[] args) {
        chap1.temp.IntSort obj = (IntSort) new chap1.temp.BubbleSort();
        chap1.temp.SortTest.setIntSort(obj);
        chap1.temp.SortTest.simpTest(new int[]{5, 8, 5, 4, 3, 5, 2, 7, 5, 9});
    }
}