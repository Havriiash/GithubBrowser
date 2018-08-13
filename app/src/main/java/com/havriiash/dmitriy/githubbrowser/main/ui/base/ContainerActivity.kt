package com.havriiash.dmitriy.githubbrowser.main.ui.base

import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar

interface ContainerActivity {

    fun showError(msg: String)

    fun navigate(fragment: Fragment, isAddToBackStack: Boolean = true)

    fun isMain(): Boolean

    fun getContainerSupportActionBar(): ActionBar?
}