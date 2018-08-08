package com.havriiash.dmitriy.githubbrowser.global

import android.os.Handler
import android.os.Looper
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor

object Constants {

    const val SERVER_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ"

    val YEAR_MONTH_DAY_FORMATTER = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    class MainThreadExecutor : Executor {
        private val handler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable?) {
            handler.post(command)
        }
    }
}