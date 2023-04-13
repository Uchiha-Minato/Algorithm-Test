package testSource;

/**
 * 测试对象
 * */
public class Coverage {
    public int funcCoverage (int x, int y, int z) {
        if((x > 3) && (z > 10))
            x++;
        else
            x--;
        if(( y == 4 ) || (z < 12 ))
            z++;
        else
            z--;
        return x+y+z;
    }

    public static void main(String[] args) {
        Coverage coverage = new Coverage();
        System.out.println(coverage.funcCoverage(2, 0, 11));
    }
}
