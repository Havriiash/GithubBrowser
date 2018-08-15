package com.havriiash.dmitriy.githubbrowser.main.ui.base

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.widget.Toast
import com.havriiash.dmitriy.githubbrowser.R
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity(), ContainerActivity {

    override fun showError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    abstract fun showProgress(progress: Boolean)


    override fun navigate(fragment: Fragment, isAddToBackStack: Boolean) {
        navigate(fragment, isAddToBackStack, false)
    }

    override fun isMain(): Boolean = supportFragmentManager.backStackEntryCount == 0

    override fun getContainerSupportActionBar(): ActionBar? = supportActionBar

    override fun getContainerActivity(): Context = this

    protected fun navigate(fragment: Fragment, isAddToBackStack: Boolean = true, isClearBackStack: Boolean = false) {
        if (isClearBackStack) {
            clearBackStack()
        }
        if (isAddToBackStack) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.activity_main_container, fragment)
                    .addToBackStack(fragment::class.java.name)
                    .commit()
        } else {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.activity_main_container, fragment)
                    .commit()
        }
    }

    private fun clearBackStack() {
        for (i in 0..supportFragmentManager.backStackEntryCount) {
            supportFragmentManager.popBackStack()
        }
    }
}