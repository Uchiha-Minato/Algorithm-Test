package algorithm.DivideAndConquer;
import Util.Input;
import java.util.Arrays;

/**
 * @author M1nato1
 * @date 2021/7/6 20:47
 * @product IntelliJ IDEA
 */
public class MergeSort{

    public static void mergeSort(int[] a, int left, int right){
        if(left < right){
            int mid = (left + right)/2;
            mergeSort(a, left, mid);
            mergeSort(a, mid + 1, right);
            merge(a, left, mid, right);//合并为数组a
            //copy(a, b, left, right);//复制为数组a
        }
    }

    public static void merge(int[] c,int l, int m, int r){
        int [] d = new int[ r - l + 1];
        int i = l, j = m + 1, k = 0;
        while((i <= m) && (j <= r)){
            if(c[i] <= c[j])
                d[k++] = c[i++];//先把小的值赋给d[i]
            else
                d[k++] = c[j++];
        }
        while(i <= m){//把左边剩余数移入数组
            d[k++] = c[i++];
        }
        while(j <= r){//把右边剩余数移入数组
            d[k++] = c[j++];
        }
        System.out.println(Arrays.toString(d));
        /*System.arraycopy(d,0,c,0,d.length);*/
        //复制数组d回c(a)
        System.arraycopy(d, 0, c, l, d.length);
    }

    public static void main(String[]args){
        /*int [] a = { 22, 1, 56, 2, 34, 3};*/

        int [] a = Input.inputArray();

        mergeSort(a,0, a.length - 1);
        System.out.println("排序后:");
        for (int j : a) {
            System.out.print(j + " ");
        }
    }
}
