package com.havriiash.dmitriy.spuilib.ui

interface BaseUIActions {
    fun showError(msg: String)

    fun showLoading(visibility: Boolean)

    fun getUITitle(): String
}