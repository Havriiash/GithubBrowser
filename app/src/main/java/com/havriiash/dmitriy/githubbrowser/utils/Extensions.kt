package com.havriiash.dmitriy.githubbrowser.utils

import com.havriiash.dmitriy.githubbrowser.global.Constants
import java.util.*

fun Date.yearMonthDayStr(): String {
    return Constants.YEAR_MONTH_DAY_FORMATTER.format(this)
}