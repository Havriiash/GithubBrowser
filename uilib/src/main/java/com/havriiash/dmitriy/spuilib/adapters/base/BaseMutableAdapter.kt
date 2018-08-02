package com.havriiash.dmitriy.spuilib.adapters.base

import com.havriiash.dmitriy.spuilib.adapters.viewholders.base.BaseViewHolder

abstract class BaseMutableAdapter<D, VH: BaseViewHolder<D>>(
        dataList: ArrayList<D?>
): BaseAdapter<D, VH>(dataList) {

    companion object {
        const val ITEM_DATA = 1
        const val ITEM_PROGRESS = 2
    }

    var isLoading = false
    private set

    fun showLoading(visibility: Boolean) {
        isLoading = visibility
        val index = dataList.size
        if (isLoading) {
            dataList.add(null)
            notifyItemInserted(index)
        } else {
            dataList.remove(null)
            notifyItemRemoved(index)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (dataList[position] == null) {
            ITEM_PROGRESS
        } else {
            ITEM_DATA
        }
    }

}
