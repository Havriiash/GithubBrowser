package com.havriiash.dmitriy.githubbrowser.main.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.havriiash.dmitriy.githubbrowser.R
import kotlinx.android.synthetic.main.view_error.view.*

class ErrorView(
        context: Context,
        attributeSet: AttributeSet
) : LinearLayout(context, attributeSet) {

    init {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ErrorView, 0, 0)
        val errorText = typedArray.getString(R.styleable.ErrorView_errorText)
        val btnRetryVisibility = typedArray.getBoolean(R.styleable.ErrorView_retryBtn, true)
        typedArray.recycle()

        orientation = VERTICAL
        gravity = Gravity.CENTER

        LayoutInflater.from(context).inflate(R.layout.view_error, this, true)

        view_error_text.text = errorText
        view_error_retryBtn.visibility = if (btnRetryVisibility) View.VISIBLE else View.GONE
    }

    fun setOnRetryClickListener(listener: OnClickListener) {
        view_error_retryBtn.setOnClickListener(listener)
    }

    fun setErrorText(text: String) {
        view_error_text.text = text
    }

}