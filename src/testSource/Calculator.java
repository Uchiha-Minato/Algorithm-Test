package testSource;

public class Calculator {
    public static void main(String[] args) {
        Calculator cal = new Calculator();
        System.out.println(cal.divide(1, 2));
        System.out.println(cal.divide(2, 3));
        System.out.println(cal.divide(2, 0));
    }

    public double divide(double num1, double num2){
        if(num1 > 0 && num2 >= 0){
            return num1 / num2;
        } else {
            return 0;
        }
    }
}
