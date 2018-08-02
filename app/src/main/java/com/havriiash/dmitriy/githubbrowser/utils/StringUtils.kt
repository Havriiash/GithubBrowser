package com.havriiash.dmitriy.githubbrowser.utils

import java.text.DecimalFormat

object StringUtils {
    private val UNITS = arrayOf("B", "KB", "MB", "GB", "TB")
    private val UNIT_FORMAT = DecimalFormat("#.##0.#")

    fun fileSizeToString(size: Long): String {
        return if (size <= 0) {
            "0"
        } else {
            val digitGroups = Math.log10(size.toDouble()) / Math.log10(1024.0)
            "${UNIT_FORMAT.format(size / Math.pow(1024.0, digitGroups))} ${UNITS[digitGroups.toInt()]}"
        }
    }

}