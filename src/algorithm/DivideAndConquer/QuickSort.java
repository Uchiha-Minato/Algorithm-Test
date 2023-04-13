package algorithm.DivideAndConquer;
import java.util.*;
import Util.Input;
/**
 * @author M1nato1
 * @date 2021/7/6 21:54
 * @product IntelliJ IDEA
 */
public class QuickSort {

    private static void qSort(int[] a, int l, int r) {
        if (l > r) {//至少有两个元素
            return;
        }
        int i, j, index;
        i = l;//左哨兵
        j = r;//右哨兵
        index = a[i];//用数组的第一个元素作为基准元素
        while (i < j) {//左哨兵的值小于右哨兵的值，从表的两端交替向中间遍历
            while (i < j && a[j] >= index)//先从右边往左边找，直到当前值比基准元素大
                j--;
            if (i < j)//用比基准值小的元素替换低位的记录
                a[i++] = a[j];
            while (i < j && a[i] < index)//然后再从左边往右边找，直到当前值比基准元素小
                i++;
            if (i < j)//用比基准元素大的值替换高位记录
                a[j--] = a[i];
        }//完成一次快速排序
        a[i] = index;//将当前基准元素替换回数组第一个元素

        System.out.println("基准元素: " + a[i]);
        System.out.println("排序后:");
        System.out.println(Arrays.toString(a));
        System.out.println("--------------------");

        //递归地对高子表和低子表排序
        qSort(a, l, i - 1);
        qSort(a, i + 1, r);
    }

    public static void main(String[] args) {
        int[] a = Input.inputArray();
        qSort(a, 0,a.length-1);
        System.out.println(Arrays.toString(a));
    }
}
