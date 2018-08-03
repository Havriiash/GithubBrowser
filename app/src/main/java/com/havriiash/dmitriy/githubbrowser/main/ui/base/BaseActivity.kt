package com.havriiash.dmitriy.githubbrowser.main.ui.base

import android.widget.Toast
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    fun showError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    abstract fun showProgress(progress: Boolean)
}