package kotlin1

interface Study {
    fun readBooks()
    fun doHomework(){
        println("do homework after implementation")
        //若在接口中定义了函数体，实现接口时就不强制override
    }
}