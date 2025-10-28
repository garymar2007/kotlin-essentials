package com.gary.com.gary.basics

fun main() {
    // statically-type language
    val meaningOfLife: Int = 42
    // val cannot be reassigned as it is immutable

    var objectiveInLife: Int = 32
    // var can be reassigned as it is mutable
    objectiveInLife = 42

    println("Basics")

    // type-inference = compiler figures out the type from the RHS of the assignment
    val meaningOfLife2 = 42

    val aBoolean: Boolean = true
    val aChar: Char = 'a'
    val aString: String = "Hello, World!"
    val anIntRange: IntRange = 1..10
    val aFloat: Float = 1.0f    //4 bytes
    val aDouble: Double = 1.0   // 8 bytes
    val aLong: Long = 1L        // 8 bytes
    val aInt: Int = 1           // 4 bytes
    val aByte: Byte = 1
    val aShort: Short = 1       // 2 bytes
}

//top-level values = constants
const val appWideMol: Int = 42