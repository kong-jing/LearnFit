fun main(args: Array<String>) {

//    print(getStringLength(3))
//  val items = listOf<String>("apple", "banana", "kiwifruit")
//  testList(items)
    val isEmpty = hasEmpty("张三", "li", "")
    println(isEmpty)
}

fun sum(a: Int, b: Int) = a + b

fun printSum(a: Int, b: Int) {
    val z: Int = 3
    println("sum of $a and $b is ${a + b} " + z)
}

fun maxOf(a: Int, b: Int): Int {

    if (a > b) {
        return a
    } else {
        return b


    }
}

//  fun printProduct(arg1: String, arg2: String) {
//    val x = parseInt(arg1)
//    val y = parseInt(arg2)
//
//    if (x != null && y != null) {
//      println(x * y )
//    }
//
//  }

//  fun parseInt(str: String): Int? {
//    return Integer.valueOf(str)
//  }
fun test1(x: Long, y: Long) {
    val quantity: Int = 5
    val price = 22.3
    val name: String = "大米"

    println("单价：$price")
    println("数量：$quantity")
    println("产品:$name 总计: ${quantity * price}")


}

fun test2(x: Int, y: Int, array: Array<Int>) {
    if (x in 1..y - 1) {
        println("ok")
    }
    if (x !in 0..array.lastIndex) {
        println("out")
    }
    val z: Int = 2
    for (z in 1..5) {
        print("$z ")
    }

    for (x in array) {
        println(x)
    }

    if (x in array) {
        println("yes")
    }
}

/**
 * when 表达式
 */
fun test3(obj: Any) {
    when (obj) {
        1 -> print("第一项")
        "hello" -> print("这是一个字符串hello")
        is Long -> print("这是一个Long类型的数据")
        !is String -> print("这不是String类型的数据")
        else -> print("lese类似于java中的default")
    }
}

fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        return obj.length
    }
    if (obj !is String) {
        print("不是字符串")
    }

    return null
}

fun testList(items: List<String>) {
    for (z in items.indices) {
        println("item at $z is ${items[z]}")
    }

    for (x in 1..10 step 2) {
        print(x)
    }
}

fun hasEmpty(vararg strArray: String?): Boolean {
    for (str in strArray) {
        if ("".equals(str) || str == null) {
            return true
        }
    }
    return false
}