package com.gary.com.gary.basics

import java.lang.Character.isLetter

fun main() {
    println("Please enter a string:")
    val input: String = readln()

    val lettersOnly = input.myFilter {
        isLetter()
    }

    println(lettersOnly)
}

/**
 * Filters the characters in the string based on a given predicate and
 * returns a new string containing only the characters that satisfy the predicate.
 *
 * @param predicate A lambda function that determines whether a character
 *                  should be included in the resulting string.
 * @return A new string containing only the characters that meet the predicate condition.
 *
 * NB: An extension function named myFilter for the String type.
 * predicate: Char.() -> Boolean: The function takes a lambda with a receiver of type Char and returns a Boolean.
 */
fun String.myFilter(predicate: Char.() -> Boolean): String {
    return buildString {
        for (char in this@myFilter) {
            if (char.predicate()) {
                append(char)
            }
        }
    }
}