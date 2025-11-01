package com.gary.com.gary.basics

fun main() {
    println("Please enter a number:")
    val input: String = readln()
    val inputAsInteger = try {
        input.toInt()
    } catch (e: NumberFormatException) {
        println("Invalid input: ${e.message}")
        0
    }

    val output = when (inputAsInteger) {
        3 -> "You entered 3"
        5 -> "You entered 5"
        in 4 ..20 -> "You entered a number between 4 and 20"
        else -> "I have no idea what you entered"
    }

    println(output)
}