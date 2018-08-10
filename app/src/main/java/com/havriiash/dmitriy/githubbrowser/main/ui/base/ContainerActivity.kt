package com.havriiash.dmitriy.githubbrowser.main.ui.base

import android.support.v4.app.Fragment

interface ContainerActivity {

    fun showError(msg: String)

    fun navigate(fragment: Fragment, isAddToBackStack: Boolean = true)

}