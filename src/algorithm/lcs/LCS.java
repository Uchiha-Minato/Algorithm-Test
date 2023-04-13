package algorithm.lcs;

import java.util.Scanner;

public class LCS {
    //计算最优值
    public static int lcsLength(char []x, char []y, int [][]b) {
        int m = x.length - 1;
        int n = y.length - 1;
        int [][]c = new int [m+1][n+1];
        for(int i = 0; i <= m; i++)
            c[i][0] = 0;
        for(int i = 0; i <= n; i++)
            c[0][i] = 0;

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(x[i] == y[j]) {
                    c[i][j] = c[i-1][j-1] + 1;//来自左上
                    b[i][j] = 1;
                }
                else if(c[i-1][j] >= c[i][j-1]) {
                    c[i][j] = c[i-1][j];//来自上方
                    b[i][j] = 2;
                }
                else {
                    c[i][j] = c[i][j-1];//來自左邊
                    b[i][j] = 3;
                }
            }
        }

        System.out.println("\nc矩阵：");
        for(int i = 0 ; i <= m; i++) {
            for(int j = 0 ; j <= n; j++) {
                System.out.print(c[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("---------");
        System.out.println("\nb矩阵：");
        for(int i = 0 ; i <= m; i++) {
            for(int j = 0 ; j <= n; j++) {
                System.out.print(b[i][j] + "\t");
            }
            System.out.println();
        }
        return c[m][n];
    }


    //构造最长公共子序列
    public static void lcs(int i, int j, char []x, int [][]b) {
        if(i == 0 || j == 0)
            return;
        if(b[i][j] == 1) {
            lcs( i - 1, j - 1, x, b);
            System.out.print(x[i]);
        }
        else if(b[i][j] == 2)
            lcs( i - 1, j, x, b);
        else
            lcs( i, j - 1, x, b);
    }


    public static void main(String [] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要比較的第一個字符串:");
        char []A = sc.nextLine().toCharArray();
        System.out.println("请输入要比較的第二個字符串:");
        char []B = sc.nextLine().toCharArray();

        System.out.println("---------");

        int [][]b = new int [A.length + 1][B.length + 1];
        char []c = new char [A.length + 1];
        char []d = new char [B.length + 1];
        System.arraycopy(A, 0, c, 1, A.length);
        System.arraycopy(B, 0, d, 1, B.length);

        int n = lcsLength(c,d,b);

        System.out.println("最長公共子串是:");
        lcs(A.length,B.length,c,b);

        System.out.println("\n最長公共字串的長度: " + n);

    }

}
