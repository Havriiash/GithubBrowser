package com.havriiash.dmitriy.githubbrowser.utils

import android.text.format.DateUtils
import com.havriiash.dmitriy.githubbrowser.global.Constants
import java.util.*

fun Date.yearMonthDayStr(): String {
    return Constants.YEAR_MONTH_DAY_FORMATTER.format(this)
}

fun Date.relativeTimeStr(): String {
    return DateUtils.getRelativeTimeSpanString(this.time, Date().time, DateUtils.SECOND_IN_MILLIS).toString()
}