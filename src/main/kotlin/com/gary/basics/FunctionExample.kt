package com.gary.com.gary.basics

// Unit == void
fun simpleFunction(arg: String): Unit {
    println("Just passed an argument: $arg")    // string template or interpolation
}

fun printHello() {
    println("Hello World! - no-arg function")
}

// concatenate a string count times
fun concatenate(str1: String, count: Int): String {
    val list = mutableListOf<String>()
    for (i in 1..count) {
        list.add(str1)
    }

    return list.joinToString(" ")
}

// special syntax for single expression functions
fun combineStrings(strA: String, strB: String): String = "$strA--$strB"

// recursive function to combine strings
fun combineStringsRecursive(strA: String, strB: String): String =
    if (strB.isEmpty()) strA else combineStringsRecursive(strA + strB.first(), strB.drop(1))

fun concatenateRecursive(str1: String, count: Int): String =
    if (count == 0) "" else if (count == 1) str1 else str1 + concatenateRecursive(str1, count - 1)

//recursion
fun factorial(n: Long): Long = if (n <= 1) 1 else n * factorial(n - 1)

// default args
fun demoDefaultArgs(a: Int = 0, b: String = "") {
    println("a = $a, b = $b")
}

fun complexFunction(outerArg: String) {
    // very complex logic
    fun innerFunction(innerArg: Int) {
        println("Outer arg: $outerArg, inner arg: $innerArg")
    }

    innerFunction(10)
}

/**
 * Exercises:
 *  1. greeting function (name, age) -> "Hi, my name is $name, I'm $age years old"
 *  2. factorial function (n) -> n!
 *  3. fibonacci function (n) -> fib(n) = fib(n-1) + fib(n-2)
 *  4. reverse string function
 *  5. isPalindrome function
 *  6. isPermutation function
 *  7. isSubstring function
 *  8. isAnagram function
 *  9. isPalindromeRecursive function
 *  10. isPermutationRecursive function
  */
fun greeting(name: String, age: Int) = "Hi, my name is $name, I'm $age years old"

fun fibonacci(n: Int): Int = if (n == 1) 1 else if (n == 2) 2 else fibonacci(n - 1) + fibonacci(n - 2)

fun reverseString(str: String): String = str.reversed()

fun isPalindrome(str: String): Boolean = str == reverseString(str)

fun isPermutation(strA: String, strB: String): Boolean = strA.toList().sorted() == strB.toList().sorted()

fun isSubstring(str: String, subStr: String): Boolean = str.contains(subStr)

fun isAnagram(strA: String, strB: String): Boolean = strA.toList().sorted() == strB.toList().sorted() && strA.length == strB.length

fun main() {
    simpleFunction("Hello World!")
    simpleFunction("Kotlin")
    simpleFunction("Scala")
    printHello()
    println(concatenate("Hello", 5))
    println(combineStrings("Hello", "World"))
    println(factorial(10))
    println(concatenateRecursive("good", 5))
    println(combineStringsRecursive("nice", "World"))
    demoDefaultArgs()
    demoDefaultArgs(10)     // do not need to name arguments in the right order
    demoDefaultArgs(b = "Kotlin")   // need to name arguments in the right order
    demoDefaultArgs(a = 10, b = "Kotlin")
    complexFunction("Kotlin")
}