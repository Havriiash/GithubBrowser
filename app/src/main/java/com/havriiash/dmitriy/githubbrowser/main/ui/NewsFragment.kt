package com.havriiash.dmitriy.githubbrowser.main.ui

import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.News
import com.havriiash.dmitriy.githubbrowser.data.source.BaseListDataSource
import com.havriiash.dmitriy.githubbrowser.data.source.NewsDataSource
import com.havriiash.dmitriy.githubbrowser.databinding.FragmentNewsBinding
import com.havriiash.dmitriy.githubbrowser.databinding.LayoutRecyclerViewBinding
import com.havriiash.dmitriy.githubbrowser.main.models.interfaces.NewsModel
import com.havriiash.dmitriy.githubbrowser.main.ui.adapters.NewsAdapter
import com.havriiash.dmitriy.githubbrowser.main.ui.base.BaseListFragment
import com.havriiash.dmitriy.githubbrowser.main.vm.NewsViewModel
import com.havriiash.dmitriy.githubbrowser.main.vm.factory.NewsVMProviderFactory
import javax.inject.Inject

class NewsFragment : BaseListFragment<News, NewsModel>() {

    @Inject
    protected lateinit var factory: NewsVMProviderFactory

    @Inject
    protected lateinit var newsSource: NewsDataSource

    private lateinit var viewModel: NewsViewModel

    private lateinit var binding: FragmentNewsBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this, factory).get(NewsViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)

        setupListView()
        return binding.root
    }

    override val layoutListViewBinding: LayoutRecyclerViewBinding
        get() = binding.fragmentNewsLayoutRecyclerView!!

    override val dataSource: BaseListDataSource<News, NewsModel>
        get() = newsSource

    override val pageSize: Int
        get() = 10

    override fun getAdapter(): PagedListAdapter<News, out RecyclerView.ViewHolder> = NewsAdapter()
}
