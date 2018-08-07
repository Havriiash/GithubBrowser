package com.havriiash.dmitriy.githubbrowser.main.ui.adapters

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.News
import com.havriiash.dmitriy.githubbrowser.databinding.ItemListNewsBinding

class NewsAdapter : PagedListAdapter<News, NewsAdapter.NewsViewHolder>(DIFFUTIL) {

    companion object DIFFUTIL : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News?, newItem: News?): Boolean {
            return oldItem?.id == newItem?.id
        }

        override fun areContentsTheSame(oldItem: News?, newItem: News?): Boolean {
            return oldItem?.actor == newItem?.actor &&
                    oldItem?.createdAt == newItem?.createdAt
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding: ItemListNewsBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_list_news, parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.setInfo(getItem(position))
    }


    class NewsViewHolder(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){

        private lateinit var binding: ItemListNewsBinding

        constructor(binding: ItemListNewsBinding): this(binding.root) {
            this.binding = binding
        }

        fun setInfo(data: News?) {
            if (data == null) {
                binding.itemListNewsContentLayout.visibility = View.GONE
                binding.itemListNewsProgressLayout.visibility = View.VISIBLE
            } else {
                binding.itemListNewsProgressLayout.visibility = View.GONE
                binding.itemListNewsContentLayout.visibility = View.VISIBLE
                binding.news = data
                handleNews()
            }
        }

        fun handleNews() { }
    }
}