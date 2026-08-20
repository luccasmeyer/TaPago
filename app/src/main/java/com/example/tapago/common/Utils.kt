package com.example.tapago.common

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun convertForDouble(string: String): Double {
    val double = string.toDoubleOrNull() ?: 0.0
    return double
}

fun convertDayWeekCompleted(item: Int): String? {
    val listDayOf = mapOf(
        0 to "monday",
        1 to "tuesday",
        2 to "wednesday",
        3 to "thursday",
        4 to "friday",
        5 to "saturday",
        6 to "sunday"
    )

    return listDayOf[item]
}

fun getGoalProfile(item: Int): String {
    if (item == 0){
        return "muscle_mass"
    } else if (item == 1){
        return "lose_weight"
    } else {
        return ""
    }
}

fun hideKeyboard(view: View) {
    val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}
