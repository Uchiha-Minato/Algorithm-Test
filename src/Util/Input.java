package Util;

import java.util.Scanner;

/**
 * @author M1nato1
 * @date 2021/7/6 22:14
 * @product IntelliJ IDEA
 */
public class Input {
    public static int[] inputArray(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要排序的数组,以逗号隔开:");
        String string = sc.next();
        String []arr = string.split(",");
        int [] a = new int[arr.length];
        for(int j = 0; j < a.length; j++){
            a[j] = Integer.parseInt(arr[j]);
        }
        return a;
    }
}
