package com.havriiash.dmitriy.githubbrowser.main.ui

import android.os.Bundle
import com.havriiash.dmitriy.githubbrowser.R
import dagger.android.support.DaggerAppCompatActivity

class NewsActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
    }

}
