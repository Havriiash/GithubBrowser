package com.havriiash.dmitriy.githubbrowser.main.ui.adapters

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Starred
import com.havriiash.dmitriy.githubbrowser.databinding.ItemListStarredBinding
import com.havriiash.dmitriy.spuilib.adapters.itemlisteners.ItemClickListener

class StarredAdapter(
        private val itemClickListener: ItemClickListener<Starred>?
) : PagedListAdapter<Starred, StarredAdapter.StarredViewHolder>(StarredDiffUtil) {

    private object StarredDiffUtil : DiffUtil.ItemCallback<Starred>() {
        override fun areItemsTheSame(oldItem: Starred?, newItem: Starred?): Boolean = oldItem?.id == newItem?.id

        override fun areContentsTheSame(oldItem: Starred?, newItem: Starred?): Boolean {
            return oldItem?.description == newItem?.description &&
                    oldItem?.owner?.avatarUrl == newItem?.owner?.avatarUrl &&
                    oldItem?.stargazersCount == newItem?.stargazersCount &&
                    oldItem?.forks == newItem?.forks &&
                    oldItem?.owner?.login == newItem?.owner?.login
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarredAdapter.StarredViewHolder {
        val binding: ItemListStarredBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_list_starred, parent, false)
        return StarredViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StarredAdapter.StarredViewHolder, position: Int) {
        holder.setInfo(getItem(position))
    }


    inner class StarredViewHolder private constructor(
            itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private lateinit var starredBinding: ItemListStarredBinding

        constructor(binding: ItemListStarredBinding) : this(binding.root) {
            starredBinding = binding
        }

        fun setInfo(data: Starred?) {
            if (data != null) {
                if (itemClickListener != null) {
                    itemView.setOnClickListener { itemClickListener.onItemClick(data) }
                }
                starredBinding.starred = data
            }
        }
    }

}