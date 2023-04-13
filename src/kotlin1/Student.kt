package kotlin1

/**
 * public class Student extends Person implements Study
 * */
class Student(sno: String, grade: Int, name: String, age: Int): Person(name, age) , Study {
    //主构造函数，实例化时必须初始化的变量
    //子类构造函数必须调用父类的构造函数
    //次构造函数必须调用主构造函数
    constructor(name: String, age: Int) : this("", 0, name, age)
    constructor() : this("",0)

    init {
        println("\nsno is $sno, grade is $grade")
    }

    override fun readBooks() {
        println("$name is reading.")
    }

/*
    override fun doHomework() {
        println("$name is doing homework")
    }
*/
}