package com.havriiash.dmitriy.githubbrowser.utils

import android.text.format.DateUtils
import android.view.View
import com.havriiash.dmitriy.githubbrowser.global.Constants
import java.util.*

fun Date.yearMonthDayStr(): String {
    return Constants.YEAR_MONTH_DAY_FORMATTER.format(this)
}

fun Date.relativeTimeStr(): String {
    return DateUtils.getRelativeTimeSpanString(this.time, Date().time, DateUtils.SECOND_IN_MILLIS).toString()
}

fun View.setViewEnabled(enabled: Boolean) {
    val alpha = if (enabled) 1f else 0.5f
    this.alpha = alpha
}