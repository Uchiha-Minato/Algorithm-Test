package algorithm.Backtrack;

import java.util.*;
/**
 * @author M1nato1
 * @date 2021/5/30 23:38
 * @product IntelliJ IDEA
 */

public class BacktrackAlgorithm {
    static int n;//货箱数目
    static int[] w;//货箱重量数组
    static int c;//第一艘船的重量
    static int cw;//当前装载的重量
    static int bestw;//目前最优装载的重量
    static int r;//剩余货箱的重量

    static int[] x;//当前解，记录从根至当前结点的路径
    static int[] bestx;//记录当前最优解


    public static int MaxLoading(int[] ww, int cc) {
        //初始化数据成员，数组下标从1开始
        n = ww.length - 1;
        w = ww;
        c = cc;        cw = 0;
        bestw = 0;
        x = new int[n+1];
        bestx = new int[n+1];

        //初始化r，即剩余最大重量
        for(int i =1;i<=n;i++) {
            r += w[i];
        }

        //计算最优载重量
        backtrack(0);
        return bestw;
    }

    //回溯算法
    public static void backtrack(int t) {
        if(t>n) {//到达叶结点
            if(cw>bestw) {
                for(int i=1;i<=n;i++) {
                    bestx[i] = x[i];
                }
                bestw = cw;
            }
            return;
        }

        r -= w[t];
        if(cw + w[t] <= c) {//搜索左子树(约束函数)
            x[t] = 1;
            cw += w[t];
            backtrack(t+1);
            cw -= w[t];//回溯
        }

        if(cw + r>bestw) {
            x[t] = 0;//搜索右子树(上界函数)
            backtrack(t+1);
        }

        r += w[t];//恢复现场

    }
    /*
     * 如果当前节点的右子树不可能包含比当前最优解更好的解时，就不移动到右子树上！
    设bestw为当前最优解，Z为解空间树的第i 层的一个节点
     为剩余货箱的重量;当cw+r<=bestw时，没有必要去搜索Z 的右子树：
     当前载重量cw+剩余集装箱的重量r当前最优载重量bestw
     */
    public static void main(String[] args){
        //int []w = new int[n+1];
        int [] w = new int[6];
        w[0] = 0;
        int c1, c2;
        int n = w.length - 1;

        System.out.println("请输入两艘轮船的载重量，以空格隔开: ");
        Scanner sc = new Scanner(System.in);
        c1 = sc.nextInt();
        c2 = sc.nextInt();

        System.out.println("请输入集装箱重量，以空格隔开: ");
        for(int i = 1; i <= n; i++){
            w[i] = sc.nextInt();
        }

        MaxLoading(w,c1);
        System.out.println("第一艘船的载重量为:" + c1 + ",第二艘船载重量为:" + c2);
        System.out.println(w.length - 1 + "个集装箱，重量分别为:");
        for(int i = 1; i <= n; i++){
            System.out.print(w[i] + " ");
        }
        System.out.println();
        int weight2 = 0;
        for(int i = 1; i <= n; i++){
                //第一艘轮船装完后的剩余重量
                weight2 += w[i]*(1 - bestx[i]);//bestx的值只能为0或者1
        }
        if(weight2 > c2)
            System.out.println("不存在可行解");
        else {
            System.out.println("所有集装箱能装上两艘船，最优解为:");
            for(int i = 1; i <= n; i++){
                System.out.print(bestx[i] + " ");
            }
            System.out.println();

            System.out.print("集装箱: ");
            for (int i = 1; i <= n; i++){
                if(bestx[i] == 1)
                    System.out.print( i + "(重量" + w[i] + ") ");
            }
            System.out.println("装入第一艘船");

            System.out.print("集装箱: ");
            for (int i = 1; i <= n; i++){
                if(bestx[i] == 0)
                    System.out.print( i + "(重量" + w[i] + ") ");
            }
            System.out.println("装入第二艘船");
        }
    }
}
