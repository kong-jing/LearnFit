package com.fengguncao.libkotlin


/**
 * name: BaseClass<p>
 * description <p>
 * @author YCKJ0165 <P>
 * date: 5/29/2020 <p>
 * copy: 重庆中科云从科技有限公司 <p>
 */
fun main() {
//    val d = Derived("hello", "world")
//    val b = StringUtils.isEmpty2("123")

//    println(b)
    Persons("张三").sayHello()
    Persons("王五", "一个好人")
    Persons("赵龙", "感觉清爽", "发现一个灵感")
}

open class Base(val name: String) {
    init {
        println("Initializing Base")
    }

    open val size : Int = name.length.also {
        println("Initializing size in Base: $it")
    }
}

class Derived(name: String, val lastName: String) : Base(name.capitalize().also {
    println("Argument for Base: $it $it")
}) {
    init {
        println("Initializing Derived")
    }

    override val size: Int =
        (super.size + lastName.length).also { println("Initializing size in Derived: $it") }
}

object StringUtils {
    @JvmStatic fun isEmpty(str: String): Boolean {
        return "" == str
    }

    fun isEmpty2(str: String): Boolean {
        return "" == str
    }

}

class Persons(private var name: String) {

    private var description: String? = null

    init {
        name = "李四"
        println(name)
    }

    fun sayHello() {
        println("hello $name")
    }

    constructor(name: String, description: String): this(name) {
        this.description = description
        println("$name 吃了五个西瓜, $description")
    }

    constructor(name: String, description: String, find: String): this(name) {
        println("$name 洗了个澡， $description , $find")
    }
}