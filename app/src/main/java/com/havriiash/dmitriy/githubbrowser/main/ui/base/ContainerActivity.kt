package com.havriiash.dmitriy.githubbrowser.main.ui.base

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar

interface ContainerActivity {

    fun showError(msg: String)

    fun navigate(fragment: Fragment, isAddToBackStack: Boolean = true)

    fun isMain(): Boolean

    fun getContainerSupportActionBar(): ActionBar?

    fun getContainerActivity(): Context

}