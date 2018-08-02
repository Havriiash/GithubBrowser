package com.havriiash.dmitriy.spuilib.adapters.base

import android.support.v7.widget.RecyclerView
import com.havriiash.dmitriy.spuilib.adapters.viewholders.base.BaseViewHolder

abstract class BaseAdapter<D, VH : BaseViewHolder<D>>(
        protected val dataList: ArrayList<D?>
) : RecyclerView.Adapter<VH>(), IAdapter<D>, ISortable<D> {

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = dataList[position]
        if (item != null) {
            holder.setInfo(item)
        }
    }

    override fun getItemCount(): Int = dataList.size

//    IAdapter implementation

    override fun addItems(newItems: List<D>) {
        if (!dataList.containsAll(newItems)) {
            val startIndex = dataList.size
            dataList.addAll(newItems)
            notifyItemRangeInserted(startIndex, newItems.size)
        }
    }

    override fun addItem(item: D) {
        if (!dataList.contains(item)) {
            dataList.add(item)
            notifyItemInserted(dataList.size)
        }
    }

    override fun changeItem(oldItem: D, newItem: D) {
        val oldItemIndex = dataList.indexOf(oldItem)
        if (oldItemIndex != -1) {
            dataList.remove(oldItem)
            dataList.add(oldItemIndex, newItem)
            notifyItemChanged(oldItemIndex)
        }
    }

    override fun deleteItems(items: List<D>) {
//       TODO: not efficient code, must be updated
        for (item: D in items) {
            deleteItem(item)
        }
    }

    override fun deleteItem(item: D) {
        val index = dataList.indexOf(item)
        if (index != -1) {
            dataList.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    override fun clear() {
        dataList.clear()
        notifyDataSetChanged()
    }

    override fun sort(unsortedList: List<D>): List<D> {
        return dataList as List<D>
    }

}