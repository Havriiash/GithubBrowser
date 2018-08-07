package com.havriiash.dmitriy.githubbrowser.main.ui.adapters

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.Follower
import com.havriiash.dmitriy.githubbrowser.databinding.ItemListFollowerBinding
import com.havriiash.dmitriy.spuilib.adapters.itemlisteners.ItemClickListener

class FollowersAdapter(
        private val itemClickListener: ItemClickListener<Follower>?
) : PagedListAdapter<Follower, FollowersAdapter.FollowersViewHolder>(FollowersDiffUtil) {

    private object FollowersDiffUtil : DiffUtil.ItemCallback<Follower>() {
        override fun areItemsTheSame(oldItem: Follower?, newItem: Follower?): Boolean = oldItem?.id == newItem?.id

        override fun areContentsTheSame(oldItem: Follower?, newItem: Follower?): Boolean {
            return oldItem?.login == newItem?.login &&
                    oldItem?.avatarUrl == newItem?.avatarUrl
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowersViewHolder {
        val binding: ItemListFollowerBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_list_follower, parent, false)
        return FollowersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowersViewHolder, position: Int) {
        holder.setInfo(getItem(position))
    }


    inner class FollowersViewHolder private constructor(
            itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private lateinit var followerBinding: ItemListFollowerBinding

        constructor(binding: ItemListFollowerBinding) : this(binding.root) {
            followerBinding = binding
        }

        fun setInfo(data: Follower?) {
            if (data == null) {
                followerBinding.itemListFollowersProgress.visibility = View.VISIBLE
            } else {
                followerBinding.itemListFollowersProgress.visibility = View.GONE
                if (itemClickListener != null) {
                    itemView.setOnClickListener { itemClickListener.onItemClick(data) }
                }
                followerBinding.follower = data
            }
        }
    }
}