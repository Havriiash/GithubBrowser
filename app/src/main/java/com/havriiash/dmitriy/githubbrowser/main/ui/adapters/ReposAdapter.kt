package com.havriiash.dmitriy.githubbrowser.main.ui.adapters

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.IShortRepoInfo
import com.havriiash.dmitriy.githubbrowser.databinding.ItemListRepoBinding
import com.havriiash.dmitriy.spuilib.adapters.itemlisteners.ItemClickListener

class ReposAdapter(
        private val itemClickListener: ItemClickListener<IShortRepoInfo>?
) : PagedListAdapter<IShortRepoInfo, ReposAdapter.ReposViewHolder>(ReposDiffUtil) {

    private object ReposDiffUtil : DiffUtil.ItemCallback<IShortRepoInfo>() {
        override fun areItemsTheSame(oldItem: IShortRepoInfo?, newItem: IShortRepoInfo?): Boolean = oldItem?.getRepoId() == newItem?.getRepoId()

        override fun areContentsTheSame(oldItem: IShortRepoInfo?, newItem: IShortRepoInfo?): Boolean {
            return oldItem?.getRepoDescription() == newItem?.getRepoDescription() &&
                    oldItem?.getRepoOwner()?.avatarUrl == newItem?.getRepoOwner()?.avatarUrl &&
                    oldItem?.getRepoStargazersCount() == newItem?.getRepoStargazersCount() &&
                    oldItem?.getRepoForksCount() == newItem?.getRepoForksCount() &&
                    oldItem?.getRepoOwner()?.login == newItem?.getRepoOwner()?.login
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val binding: ItemListRepoBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_list_repo, parent, false)
        return ReposViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        holder.setInfo(getItem(position))
    }


    inner class ReposViewHolder private constructor(
            itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private lateinit var repoBinding: ItemListRepoBinding

        constructor(binding: ItemListRepoBinding) : this(binding.root) {
            repoBinding = binding
        }

        fun setInfo(data: IShortRepoInfo?) {
            if (data != null) {
                if (itemClickListener != null) {
                    itemView.setOnClickListener { itemClickListener.onItemClick(data) }
                }
                repoBinding.repoInfo = data
            }
        }
    }

}