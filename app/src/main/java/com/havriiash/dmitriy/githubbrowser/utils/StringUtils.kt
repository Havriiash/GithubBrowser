package com.havriiash.dmitriy.githubbrowser.utils

import java.text.DecimalFormat

object StringUtils {

    @JvmStatic
    fun fileSizeToString(size: Long): String {
        val units = arrayOf("B", "KB", "MB", "GB", "TB")
        val unitFormat = DecimalFormat("#,##0.00")
        return if (size <= 0) {
            "0"
        } else {
            val digitGroups = Math.log10(size.toDouble()) / Math.log10(1024.0)
            "${unitFormat.format(size / Math.pow(1024.0, digitGroups))} ${units[digitGroups.toInt()]}"
        }
    }

}