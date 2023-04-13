package calc;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Objects;

public class Calc {

    /**
     * 验证表达式是否合理
     * */
    public String calculate(String string) throws Exception{
        //若左括号数量多于右括号或反过来，则说明表达式不合理
        int x = 0;
        //特殊情况:一上来就写一个括号，照常入栈
        if(string.charAt(0) == '('){
            x++;
            for(int i = 0; i < string.length(); i++){
                if(string.charAt(i) == '('){
                    x++;
                }else if(string.charAt(i) == ')'){
                    x--;
                }
            }
        }

        if(x < 0){
            throw new Exception("错误，左括号大于右括号");
        }

        //操作数栈
        ArrayDeque<String> value = new ArrayDeque<>();
        //OP栈
        ArrayDeque<String> operator = new ArrayDeque<>();
        //此字符串用来临时存储转化后的后缀表达式
        StringBuilder stringBuilder = new StringBuilder();
        //将中缀表达式的操作数和运算符分离并放到字符串数组中
        String[] expression = formatInput(string);

        //遍历字符串数组
        for(String s : expression){
            //若字符串为空串则跳过
            if(s.equals(" ") || s.length() == 0){
                continue;
            }
            //first
            if(expression.length == 1 && isDigital(s.charAt(0)) && !s.contains("^")){
                stringBuilder.append(s).append(" ");
            //second
            } else if(string.contains("^")){
                //求平方
                stringBuilder.append(Arrays.toString(expression));
                double results = function_square(stringBuilder);
                //将结果直接压栈
                value.push(Double.toString(results));
                stringBuilder.delete(0, stringBuilder.length());
                break;

            }else if(expression.length == 1 && isOperator(s.charAt(0))
                && isDigital(s.charAt(1))){
                stringBuilder.append("0 ").append(s.charAt(1)).
                        append(" ").append(s.charAt(0)).append(" ");

            } else if(s.charAt(0) == '-' && !s.equals("-")){
                //若是负数，则添加至后缀表达式末尾，并补上空格，方便后面将后缀表达式分离
                //deleteCharAt(2):意思是将负号删除，放到逆波兰式末尾
                stringBuilder.append("0 ").append(s).deleteCharAt(2)
                        .append(" ").append("- ");

            } else if(isDigital(s.charAt(0))){
                //若是整数，则添加至后缀表达式末尾，并补上空格，方便后面分离
                stringBuilder.append(s).append(" ");

            } else if(s.equals("sin")){
                //求正弦值
                stringBuilder.append(Arrays.toString(expression));
                double results = function_sin(stringBuilder);
                //将结果直接压入值栈
                value.push(Double.toString(results));
                stringBuilder.delete(0 ,stringBuilder.length());
                break;

            } else if(s.equals("cos")){
                //求余弦值
                stringBuilder.append(Arrays.toString(expression));
                double results = function_cos(stringBuilder);
                //将结果直接压入值栈
                value.push(Double.toString(results));
                stringBuilder.delete(0, stringBuilder.length());
                break;

            } else if(s.equals("tan")){
                //求正弦值
                stringBuilder.append(Arrays.toString(expression));
                double results = function_tan(stringBuilder);
                //将结果直接压入值栈
                value.push(Double.toString(results));
                stringBuilder.delete(0 ,stringBuilder.length());
                break;

            }else if(s.equals("sqrt")){
                //求余弦值
                stringBuilder.append(Arrays.toString(expression));
                double results = function_sqrt(stringBuilder);
                //将结果直接压入值栈
                value.push(Double.toString(results));
                stringBuilder.delete(0, stringBuilder.length());
                break;

            } else if(isOperator(s.charAt(0)) || s.charAt(0) == '(' || s.charAt(0) == ')') {
                //若为运算符，继续判断
                if(s.equals("(")) {
                    //若为左括号，则压入op栈
                    operator.push(s);

                } else if(s.equals(")")){
                    //若为右括号，则将op栈中第一个左括号以上全部弹出并添加到后缀表达式
                    for(int i = 0; i < s.length(); i++){
                        if(!operator.getFirst().equals("(")){
                            stringBuilder.append(operator.poll()).append(" ");
                        } else {
                            stringBuilder.append(operator.poll()).append(" ");
                            break;
                        }
                    }
                } else if(operator.isEmpty()){
                    //若op栈为空，则将运算符压入栈中
                    operator.push(s);
                } else if(priorityIsBiggerOrTheSame(s, operator.getFirst())){
                    operator.push(s);
                } else {
                    //否则弹出所有优先级不比他低的运算符添加至后缀表达式
                    while(!operator.isEmpty() && !priorityIsBiggerOrTheSame(s, operator.getFirst())){
                        stringBuilder.append(operator.poll()).append(" ");
                    }
                    operator.push(s);
                }
            }
        }
        //弹出操作数栈中所有元素添加至后缀表达式
        while(!operator.isEmpty()){
            stringBuilder.append(operator.poll());
        }
        //格式化后缀表达式
        expression = formatInfixExpression(stringBuilder.toString());
        //清空后缀表达式
        stringBuilder.delete(0, stringBuilder.length());
        //遍历字符串数组
        for(String s : expression) {
            //若该字符串为空，则跳过
            if(s.equals(" ") || s.length() == 0){
                continue;
            }
            if(isDigital(s.charAt(0))){
                //若为正数则压入操作数栈中
                value.push(s);
            }else if(isOperator(s.charAt(0))){
                //若为运算符则判断操作数个数大于两个，则进行弹出两个操作数操作
                //然后将结果压入操作数栈
                if(value.size() >= 2){
                    BigDecimal last = new BigDecimal(value.poll());
                    BigDecimal last_p = new BigDecimal(Objects.requireNonNull(value.poll()));
                    switch (s) {
                        case "-" -> value.push(last_p.subtract(last).toString());
                        case "+" -> value.push(last_p.add(last).toString());
                        case "*" -> value.push((last_p.multiply(last).toString()));
                        case "/" -> value.push(last_p.divide(last, 15, BigDecimal.ROUND_HALF_DOWN).toString());
                    }
                }
            }
        }
        return value.poll();
    }
    /**
     * 判断是否为数字，是则返回true
     * */
    public static boolean isDigital(int a){
        return a >= '0' && a <= '9';
    }
    /**
     * 判断是否为运算符，是则返回true
     * */
    public static boolean isOperator(int a){
        return a == '+' || a == '-' || a == '*' || a == '/';
    }
    /**
     * 格式化中缀表达式输入，在数字和操作符之间加空格，以便后面计算操作
     * */
    public static String[] formatInput(String s){
        StringBuilder tmp = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(i == 0 && c == '-'){
                tmp.append(c);
            }else if(isOperator(c) || c == '(' || c == ')'){
                if((i - 1) >= 0 && (i + 1) < s.length()
                    && (isOperator(s.charAt(i - 1)) || s.charAt(i - 1) == '(' || s.charAt(i - 1) == ' ')
                    && c == '-' && isDigital(s.charAt(i + 1))){
                    tmp.append(" ").append(c);
                }else {
                    tmp.append(" ").append(c).append(" ");
                }
            }else
                tmp.append(c);//数字不用加空格
        }
        return tmp.toString().split(" ");
    }
    /**
     * 格式化后缀表达式
     * */
    public static String[] formatInfixExpression(String s){
        StringBuilder tmp = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(isOperator(c)){
                if(c == '-' && i + 1 < s.length() && isDigital(s.charAt(i))){
                    tmp.append(" ").append(c);
                } else {
                    tmp.append(" ").append(c).append(" ");
                }
            } else {
                tmp.append(c);//数字不用加空格
            }
        }
        return tmp.toString().split(" ");//分割
    }

    /**
     * 优先级判断
     * */
    public static boolean priorityIsBiggerOrTheSame(String a, String b){
        String s = "+- */";
        return s.indexOf(a) - s.indexOf(b) >= 2;
    }

    /**
     * 三角函数计算
     * */
    public static boolean flag = true;
    public static double TriangleOperation(String str1, String str2){
        double degree;
        degree = Double.parseDouble(str1);
        double rad = Math.toRadians(degree);
        switch (str2) {
            case "tan" -> {
                if (degree == 90) {
                    return Double.POSITIVE_INFINITY;
                } else {
                    return Math.tan(rad);
                }
            }
            case "sin" -> {
                return Math.sin(rad);
            }
            case "cos" -> {
                return Math.cos(rad);
            }
            case "sqrt" -> {
                return Math.sqrt(degree);
            }
            default -> {
                return Math.log(rad);
            }
        }
    }
    //递归调用次数
    public static int RecursiveCall = 0;
    public static double function_sin(StringBuilder stringBuilder) {
        int index = stringBuilder.indexOf("sin");
        if (index >= 0) {
            //sin标识符存在
            if(RecursiveCall == 0){
                //删去sin标识符和他配套的括号
                stringBuilder.delete(0, 9).
                        delete(stringBuilder.length() - 4, stringBuilder.length());
            } else {
                //RecursiveCall > 0
                stringBuilder.delete(0, 8).
                        delete(stringBuilder.length() - 5, stringBuilder.length());
            }

            if(stringBuilder.indexOf("sin") >= 0) {
                RecursiveCall++;
                function_sin(stringBuilder);
            }
        }

        String str1 = stringBuilder.toString();
        String str2 = "sin";
        double value = TriangleOperation(str1, str2);
        stringBuilder.delete(0, stringBuilder.length()).append(value);

        return value;
    }
    public static double function_cos(StringBuilder stringBuilder) {
        int index = stringBuilder.indexOf("cos");
        if (index >= 0) {
            //cos标识符存在
            if(RecursiveCall == 0){
                //删去sin标识符和他配套的括号
                stringBuilder.delete(0, 9).
                        delete(stringBuilder.length() - 4, stringBuilder.length());
            } else {
                //RecursiveCall > 0
                stringBuilder.delete(0, 8).
                        delete(stringBuilder.length() - 5, stringBuilder.length());
            }

            if(stringBuilder.indexOf("cos") >= 0) {
                RecursiveCall++;
                function_cos(stringBuilder);
            }
        }

        String str1 = stringBuilder.toString();
        String str2 = "cos";
        double value = TriangleOperation(str1, str2);
        stringBuilder.delete(0, stringBuilder.length()).append(value);

        return value;
    }
    public static double function_tan(@NotNull StringBuilder stringBuilder) {
        int index = stringBuilder.indexOf("tan");
        if (index >= 0) {
            //cos标识符存在
            if(RecursiveCall == 0){
                //删去sin标识符和他配套的括号
                stringBuilder.delete(0, 9).
                        delete(stringBuilder.length() - 4, stringBuilder.length());
            } else {
                //RecursiveCall > 0
                stringBuilder.delete(0, 8).
                        delete(stringBuilder.length() - 5, stringBuilder.length());
            }

            if(stringBuilder.indexOf("tan") >= 0) {
                RecursiveCall++;
                function_tan(stringBuilder);
            }
        }

        String str1 = stringBuilder.toString();
        String str2 = "tan";
        double value = TriangleOperation(str1, str2);
        stringBuilder.delete(0, stringBuilder.length()).append(value);

        return value;
    }
    public static double function_sqrt(StringBuilder stringBuilder) {
        int index = stringBuilder.indexOf("sqrt");
        if (index >= 0) {
            //cos标识符存在
            if(RecursiveCall == 0){
                //删去sqrt标识符和他配套的括号
                stringBuilder.delete(0, 10).
                        delete(stringBuilder.length() - 4, stringBuilder.length());
            } else {
                //RecursiveCall > 0
                stringBuilder.delete(0, 9).
                        delete(stringBuilder.length() - 5, stringBuilder.length());
            }

            if(stringBuilder.indexOf("sqrt") >= 0) {
                RecursiveCall++;
                function_sqrt(stringBuilder);
            }
        }

        String str1 = stringBuilder.toString();
        String str2 = "sqrt";
        double value = TriangleOperation(str1, str2);
        stringBuilder.delete(0, stringBuilder.length()).append(value);

        return value;
    }

    //此flag用来判断负号是在括号里面还是括号外面(true在外面)
    //    public static boolean flag = false;
    //此flag2用来判断平方符号是在括号里面还是括号外面(true在外面)
    public static boolean flag2 = true;
    public static double function_square(StringBuilder stringBuilder){
        int index = stringBuilder.lastIndexOf("-");
        double operate_num;

        if(index < 0){
            //没有负号
            flag = false;
            if(stringBuilder.lastIndexOf("(") < 0){
                //没有括号
                /*flag2 = true;*/
                stringBuilder.deleteCharAt(0);
                stringBuilder.delete(stringBuilder.length() - 3, stringBuilder.length());
            } else {
                //有括号
                //判断平方符号是在括号里面还是括号外面
                /*sqrOutOfSymbolN(stringBuilder);*/
                if(stringBuilder.indexOf("^") > stringBuilder.indexOf(")")){
                    //平方号在括号外面
                    /*flag2 = true;*/
                    int symbol_left = stringBuilder.indexOf("(");
                    stringBuilder.delete(0, symbol_left + 2);
                    int symbol_right = stringBuilder.lastIndexOf(")");
                    stringBuilder.delete(symbol_right - 2, stringBuilder.length());

                } else {
                    //平方号在括号里面
                    /*flag2 = true;*/
                    int symbol_left = stringBuilder.indexOf("(");
                    stringBuilder.delete(0, symbol_left + 2);
                    int symbol_right = stringBuilder.lastIndexOf(")");
                    stringBuilder.delete(symbol_right - 4, stringBuilder.length());

                }
            }
        } else if(stringBuilder.indexOf("-") == stringBuilder.lastIndexOf("-")){
            //有一个负号
            if(stringBuilder.lastIndexOf("(") < 0){
                //没有括号
                flag = false;
                flag2 = false;
                stringBuilder.deleteCharAt(0);
                stringBuilder.delete(stringBuilder.length() - 3, stringBuilder.length());
            } else {
                //有括号
                if(stringBuilder.indexOf("-") < stringBuilder.indexOf("(")) {
                    //负号在括号外面
                    flag = true;
                    sqrOutOfSymbolN(stringBuilder);
                } else {
                    //负号在括号里面
                    flag = false;
                    if(stringBuilder.indexOf("^") > stringBuilder.indexOf(")")){
                        //平方号在括号外面
                        flag2 = true;
                        int symbol_left = stringBuilder.indexOf("(");
                        stringBuilder.delete(0, symbol_left + 4);
                        int symbol_right = stringBuilder.lastIndexOf(")");
                        stringBuilder.delete(symbol_right - 2, stringBuilder.length());

                    } else {
                        //平方号在括号里面
                        flag2 = false;
                        int symbol_left = stringBuilder.indexOf("(");
                        stringBuilder.delete(0, symbol_left + 4);
                        int symbol_right = stringBuilder.lastIndexOf(")");
                        stringBuilder.delete(symbol_right - 4, stringBuilder.length());
                    }
                }
            }
        } else if(stringBuilder.indexOf("-") < stringBuilder.lastIndexOf("-")){
            //有两个负号，一个在外面一个在里面

            if(stringBuilder.indexOf("^") > stringBuilder.indexOf(")")){
                //平方号在括号外面
                int symbol_left = stringBuilder.indexOf("(");
                stringBuilder.delete(0, symbol_left + 4);
                int symbol_right = stringBuilder.lastIndexOf(")");
                stringBuilder.delete(symbol_right - 2, stringBuilder.length());
                operate_num = Double.parseDouble(stringBuilder.toString());
                return -Math.pow(operate_num, 2);

            } else {
                //平方号在括号里面
                int symbol_left = stringBuilder.indexOf("(");
                stringBuilder.delete(0, symbol_left + 4);
                int symbol_right = stringBuilder.lastIndexOf(")");
                stringBuilder.delete(symbol_right - 4, stringBuilder.length());
                operate_num = Double.parseDouble(stringBuilder.toString());
                return Math.pow(operate_num, 2);

            }
        }
        operate_num = Double.parseDouble(stringBuilder.toString());

        if(flag){
            //负号在括号外面
            //平方号在括号里外都一样
            return -Math.pow(operate_num, 2);
        } else {
            if(flag2){
                //负号在里,平方号在外
                return Math.pow(operate_num, 2);
            } else {
                return -Math.pow(operate_num, 2);
            }
        }
    }
    /**
     * 判断平方号位置
     * 有一个负号,没有负号时适用
     * */
    public static void sqrOutOfSymbolN(StringBuilder stringBuilder){
        if(stringBuilder.indexOf("^") > stringBuilder.indexOf(")")){
            //平方号在括号外面
            flag2 = true;
            int symbol_left = stringBuilder.indexOf("(");
            stringBuilder.delete(0, symbol_left + 2);
            int symbol_right = stringBuilder.lastIndexOf(")");
            stringBuilder.delete(symbol_right - 2, stringBuilder.length());

        } else {
            //平方号在括号里面
            flag2 = false;
            int symbol_left = stringBuilder.indexOf("(");
            stringBuilder.delete(0, symbol_left + 2);
            int symbol_right = stringBuilder.lastIndexOf(")");
            stringBuilder.delete(symbol_right - 4, stringBuilder.length());
        }
    }
}
