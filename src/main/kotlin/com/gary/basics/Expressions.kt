package com.gary.com.gary.basics

fun main() {
    // expressions = structures that are turned into a value
    val meaningOfLife: Int = 42 + 2

    // math expression: + - * / %
    // assignment expression: = += -= *= /= %=
    // comparison expression: < > <= >= == !=
    // logical expression: && || !
    // range expression: a..b
    // as expression: a as Type
    // is expression: a is Type
    // if expression: if (condition) { ... } else { ... }
    // when expression: when (condition) { ... }
    // for expression: for (item in collection) { ... }
    // bitwise operations: shl, shr, ushr, and, or, xor, inv
    val bitwiseExpression = 2 shl 2 // 1000 = 8
    println("bitwiseExpression = $bitwiseExpression")

    // instructions and expressions - instructions are EXECUTED (imperative programming)
    // whiles expressions are VALUE (functional programming)

    val aCondition = 1 > 2
    // instruction
    if (aCondition) {
        println("aCondition is true")
    } else {
        println("aCondition is false")
    }

    // expression
    val anIfExpression = if (aCondition) "aCondition is true" else "aCondition is false"
    println("anIfExpression = $anIfExpression")

    // when - switch on steroids
    val aNumber = 10
    when (aNumber) {
        1 -> println("aNumber is 1")
        2 -> println("aNumber is 2")
        else -> println("aNumber is something else")
    }
    // when can also be used as an expression
    val aWhenExpression = when (aNumber) {
        1 -> "aNumber is 1"
        2 -> "aNumber is 2"
        else -> "aNumber is something else"
    }
    println("aWhenExpression = $aWhenExpression")

    val anything: Any = 42
    val something = when (anything) {
        is Int -> println("anything is an Int")
        is String -> println("anything is a String")
        else -> println("anything is something else")
    }

    // looping - instructions
    // for loop: NB: "i" is final and immutable inside the for loop
    println("inclusive range")
    for (i in 1..10) {
        println("i = $i")
    }
    println("exclusive range")
    for (i in 1..< 10) {
        println("i = $i")
    }
    println("exclusive range v2")
    for (i in 1 until 10) {
        println("i = $i")
    }
    println("inclusive range, step 2 range")
    for (i in 1..10 step 2) {
        println("i = $i")
    }

    println("descending range")
    for (i in 10 downTo 1) {
        println("i = $i")
    }

    // arrays
    println("iterating over collection")
    val anArray = arrayOf(1, 2, 3, 5, 9, 8, 10, 4, 7, 6)
    for (elem in anArray) {
        println("elem = $elem")
    }

    // while loop
    var i = 1
    while ( i <= 10 ) {
        println("i = $i")
        i++
    }

    // do-while
    var j = 10
    do {
        println("j = $j")
        j--
    } while (j > 0)

}