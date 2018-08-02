package com.havriiash.dmitriy.spuilib.adapters.viewholders.base

import android.support.v7.widget.RecyclerView
import android.view.View
import com.havriiash.dmitriy.spuilib.adapters.itemlisteners.ItemClickListener

abstract class BaseViewHolder<D>(
        itemView: View,
        private val vhClickListener: ItemClickListener<D>?
) : RecyclerView.ViewHolder(itemView) {
    open fun setInfo(data: D) {
        itemView.setOnClickListener { vhClickListener?.onItemClick(data) }
    }
}