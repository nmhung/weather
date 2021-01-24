package net.fitken.weather.utils

import android.text.format.DateFormat
import java.util.*


fun Long.formatDate(): String {
    val monthYearStr: String
    try {
        monthYearStr = DateFormat.format("EEEE, dd MMM yyyy", Date(this * 1000)).toString()
    } catch (e: Exception) {
        return ""
    }
    return monthYearStr
}