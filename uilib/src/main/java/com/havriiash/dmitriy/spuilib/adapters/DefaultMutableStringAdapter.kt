package com.havriiash.dmitriy.spuilib.adapters

import android.view.ViewGroup
import com.havriiash.dmitriy.spuilib.adapters.base.BaseMutableAdapter
import com.havriiash.dmitriy.spuilib.adapters.base.BaseMutableAdapter.Companion.ITEM_DATA
import com.havriiash.dmitriy.spuilib.adapters.viewholders.base.BaseViewHolder
import com.havriiash.dmitriy.spuilib.adapters.viewholders.DefaultProgressViewHolder
import com.havriiash.dmitriy.spuilib.adapters.viewholders.DefaultStringItemViewHolder

class DefaultMutableStringAdapter(
        dataList: ArrayList<String?>,
        protected val subtitleList: ArrayList<String?>?
) : BaseMutableAdapter<String>(dataList = dataList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<String> {
        return when (viewType) {
            ITEM_DATA -> DefaultStringItemViewHolder(parent, null)
            else -> DefaultProgressViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<String>, position: Int) {
        val item = dataList[position]
        if (item != null) {
            holder.setInfo(item)
            if (subtitleList != null) {
                (holder as DefaultStringItemViewHolder).setSubtitle(subtitleList[position])
            } else {
                (holder as DefaultStringItemViewHolder).setSubtitle(null)
            }
        }
    }

}