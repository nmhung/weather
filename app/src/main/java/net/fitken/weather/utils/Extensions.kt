package net.fitken.weather.utils

import android.text.format.DateFormat
import java.util.*


fun Long.timestampToDate(): String {
    val monthYearStr: String
    try {
        monthYearStr = DateFormat.format("EEE, dd MMM yyyy", Date(this * 1000)).toString()
    } catch (e: Exception) {
        e.printStackTrace()
        return ""
    }
    return monthYearStr
}

fun Long.millisecondToDate(): String {
    val monthYearStr: String
    try {
        monthYearStr = DateFormat.format("EEE, dd MMM yyyy", Date(this)).toString()
    } catch (e: Exception) {
        e.printStackTrace()
        return ""
    }
    return monthYearStr
}