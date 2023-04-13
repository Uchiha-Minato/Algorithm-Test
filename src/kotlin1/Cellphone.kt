package kotlin1

data class Cellphone(val brand: String, val price: Double)
//data:数据类
//在Java中定义数据类一般需要重写equals(),hashCode()和toString()方法
//data关键字省略了以上繁琐的操作
//没有data关键字的运行结果：
/*kotlin.Cellphone@4783da3f*/