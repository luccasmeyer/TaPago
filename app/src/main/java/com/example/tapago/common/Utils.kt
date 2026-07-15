package com.example.tapago.common

fun convertForDouble(string: String): Double{
    val double = string.toDoubleOrNull() ?: 0.0
    return double
}