package com.example.tapago.common

fun convertForDouble(string: String): Double {
    val double = string.toDoubleOrNull() ?: 0.0
    return double
}

fun convertDayWeekCompleted(item: Int): String? {
    val listDayOf = mapOf(
        0 to "sunday",
        1 to "monday",
        2 to "tuesday",
        3 to "wednesday",
        4 to "thursday",
        5 to "friday",
        6 to "saturday"
    )

    return listDayOf[item]
}