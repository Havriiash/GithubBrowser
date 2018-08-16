package com.havriiash.dmitriy.githubbrowser.main.ui.adapters

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Commit
import com.havriiash.dmitriy.githubbrowser.databinding.ItemListCommitBinding
import com.havriiash.dmitriy.spuilib.adapters.itemlisteners.ItemClickListener

class CommitsAdapter(
        private val itemClickListener: ItemClickListener<Commit>?
) : PagedListAdapter<Commit, CommitsAdapter.CommitsViewHolder>(CommitsDiffUtil) {

    object CommitsDiffUtil : DiffUtil.ItemCallback<Commit>() {
        override fun areItemsTheSame(oldItem: Commit?, newItem: Commit?): Boolean =
                oldItem?.sha == newItem?.sha

        override fun areContentsTheSame(oldItem: Commit?, newItem: Commit?): Boolean {
            return oldItem?.commit?.author?.name == newItem?.commit?.author?.name &&
                    oldItem?.commit?.message == newItem?.commit?.message &&
                    oldItem?.commit?.commiter?.date?.time == newItem?.commit?.commiter?.date?.time &&
                    oldItem?.commit?.commentCount == newItem?.commit?.commentCount
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitsViewHolder {
        val binding: ItemListCommitBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_list_commit, parent, false)
        return CommitsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommitsViewHolder, position: Int) {
        holder.setInfo(getItem(position))
    }


    inner class CommitsViewHolder private constructor(
            itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private lateinit var binding: ItemListCommitBinding

        constructor(binding: ItemListCommitBinding) : this(binding.root) {
            this.binding = binding
        }

        fun setInfo(data: Commit?) {
            if (data != null) {
                itemView.setOnClickListener { itemClickListener?.onItemClick(data) }
                binding.commitEntity = data
            }
        }
    }
}