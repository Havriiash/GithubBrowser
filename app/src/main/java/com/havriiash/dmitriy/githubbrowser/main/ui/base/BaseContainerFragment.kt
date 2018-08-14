package com.havriiash.dmitriy.githubbrowser.main.ui.base

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.databinding.FragmentContainerBinding
import com.havriiash.dmitriy.githubbrowser.main.ui.adapters.PagerFragmentAdapter
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseContainerFragment<D>: DaggerFragment(), FragmentContainerListener<D> {

    @Inject
    protected lateinit var containerActivity: ContainerActivity


    abstract val fragments: List<DaggerFragment>

    abstract val titles: List<String>

    protected abstract val pageChangeListener: ViewPager.OnPageChangeListener


    protected lateinit var adapter: PagerFragmentAdapter

    protected lateinit var containerBinding: FragmentContainerBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        containerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_container, container, false)
        setupPager()
        setupToolbar()
        return containerBinding.root
    }

    private fun setupPager() {
        adapter = PagerFragmentAdapter(childFragmentManager, fragments, titles)
        containerBinding.fragmentContainerViewPager.adapter = adapter
        containerBinding.fragmentContainerViewPager.addOnPageChangeListener(pageChangeListener)
        containerBinding.fragmentContainerTabLayout.setupWithViewPager(containerBinding.fragmentContainerViewPager)
    }

    protected fun setupToolbar() {
        if (containerActivity.isMain()) {
            containerActivity.getContainerSupportActionBar()?.setHomeAsUpIndicator(R.drawable.ic_menu)
        } else {
            containerActivity.getContainerSupportActionBar()?.setHomeAsUpIndicator(R.drawable.ic_back)
        }
        containerActivity.getContainerSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
    }
}