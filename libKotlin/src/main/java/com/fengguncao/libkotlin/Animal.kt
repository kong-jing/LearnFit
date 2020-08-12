package com.fengguncao.libkotlin


/**
 * name: Animal<p>
 * description <p>
 * @author YCKJ0165 <P>
 * date: 6/1/2020 <p>
 * copy: 重庆中科云从科技有限公司 <p>
 */
open class Animal {

}

class Dog :Animal() {
    fun bark() {
        println("animal")
    }

}

var animal: Animal? = null

object Main {
    fun Animal.bark(): String {
        return "animal"
    }

    fun  Dog.bark(): String {
        return "dog"
    }

    fun Animal.printBark(anim: Animal) {
        println(anim.bark())
    }

    @JvmStatic fun main(args: Array<String>) {
//        Animal().printBark(Dog())
//
//        if (animal is Dog) {
//            (animal as Dog).bark()
//        }
//        val mutableList: MutableList<B> = mutableListOf(B(), B(), C())
//        val list: List<A> = mutableList
//        println(list)
    }
}

open class A
open class B : A()
open class C : B()

class TypeArray<in A> {
    fun getValue(a: A): Int? {
        return a?.hashCode()
    }

//    fun getA(a: A): A? {
//        return a;
//    }
}
