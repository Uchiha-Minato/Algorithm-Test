package kotlin1

import java.util.*
import kotlin.math.max

fun main(){
    val a = 10
    val b = 20
    println(largeNum(a, b))
    val student = Student("2019011378", 3, "Uchiha_Minato", 20)
    doStudy(student)
    student.eat()//Student继承了Person类，可以直接调用Person类的函数
    val iQoo = Cellphone("vivo", 2699.99)
    println(iQoo)

    //kotlin + lambda
    val list = mutableListOf("Apple", "Banana", "Grape", "Orange", "Pear", "Watermelon")
    val anyResult = list.any { it.length <= 5 }
    println("anyResult is $anyResult")
    println(list.maxByOrNull { it.length })
    val newList = list.filter { it.length <= 5 }.map { it.uppercase(Locale.getDefault()) }
    for(fruit in newList){
        //字符串内嵌表达式,定义函数时可以给参数设置默认值
        print("$fruit ")
    }
}

fun doStudy(study : Study?) {
    //参数为Study类，但是传进来Student类实体，
    // Student又implement了study接口，因此可以接收这类实例
    //面向接口编程， 又称为多态
    //?:判空辅助工具
/*        study?.doHomework()
        study?.readBooks()*/
    //上述代码稍显啰嗦
    //let：kotlin标准函数
    //?.操作符表示对象为空时什么都不做，不为空时就调用let函数，
    //let函数会将study本身作为参数传到lambda表达式中
    //此时的study必定不为空
    study?.let { /* stu -> */
        it.doHomework()
        it.readBooks()
    }
}

fun largeNum(num1: Int, num2: Int) = max(num1, num2)
