package chap1.temp;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年11月06日 19:13
 * 项目名称:  forAlgorithms
 * 文件名称:  BubbleSort
 * 文件描述:  @Description:
 */
public class BubbleSort implements IntSort {
    // 实例变量 - 用你自己的变量替换下面的例子
    public int[] sort(int[] arr){//本书通常不关注算法！
        for(int i = 0 ; i< arr.length-1; i++){
            for(int j = 0 ; j < arr.length-i-1;j++){
                if(arr[j] > arr[j+1]){ //冒泡
                    swap(arr ,j, j+1);
                }
            }
        }
        return arr;
    }
    public static void swap(int[] arr, int one, int two) {
        if (one == two) {
            return;
        }
        int temp = arr[one];
        arr[one] = arr[two];
        arr[two] = temp;
    }

    //将被移到SortTes
}
