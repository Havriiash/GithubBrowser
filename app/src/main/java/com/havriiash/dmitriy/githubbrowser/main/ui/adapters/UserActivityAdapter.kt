package com.havriiash.dmitriy.githubbrowser.main.ui.adapters

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.data.remote.entity.User
import com.havriiash.dmitriy.githubbrowser.databinding.ItemListUserActivityBinding
import com.havriiash.dmitriy.spuilib.adapters.itemlisteners.ItemClickListener

class UserActivityAdapter(
        private val itemClickListener: ItemClickListener<User.UserActivity>?
) : PagedListAdapter<User.UserActivity, UserActivityAdapter.UserActivityViewHolder>(UserActivityDiffUtil) {

    object UserActivityDiffUtil : DiffUtil.ItemCallback<User.UserActivity>() {
        override fun areItemsTheSame(oldItem: User.UserActivity?, newItem: User.UserActivity?): Boolean = oldItem?.id == newItem?.id

        override fun areContentsTheSame(oldItem: User.UserActivity?, newItem: User.UserActivity?): Boolean {
            return oldItem?.type == newItem?.type &&
                    oldItem?.actor == newItem?.actor &&
                    oldItem?.repo == newItem?.repo
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserActivityViewHolder {
        val binding: ItemListUserActivityBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_list_user_activity, parent, false)
        return UserActivityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserActivityViewHolder, position: Int) {
        holder.setInfo(getItem(position))
    }


    inner class UserActivityViewHolder private constructor(
            itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private lateinit var binding: ItemListUserActivityBinding

        constructor(binding: ItemListUserActivityBinding) : this(binding.root) {
            this.binding = binding
        }

        fun setInfo(data: User.UserActivity?) {
            if (data != null) {
                if (itemClickListener != null) {
                    itemView.setOnClickListener { itemClickListener.onItemClick(data) }
                }
                binding.userActivity = data
            }
        }
    }
}