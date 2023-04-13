package kotlin1

open class Person(var name: String, var age: Int) {
    //非open的class不可被继承

    fun eat(){
        println("$name is eating. He is $age years old")
    }
}