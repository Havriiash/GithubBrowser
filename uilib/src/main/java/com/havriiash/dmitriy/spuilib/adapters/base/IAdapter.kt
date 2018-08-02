package com.havriiash.dmitriy.spuilib.adapters.base

internal interface IAdapter<D> {
    fun addItems(newItems: List<D>)

    fun addItem(item: D)

    fun changeItem(oldItem: D, newItem: D)

    fun deleteItems(items: List<D>)

    fun deleteItem(item: D)

    fun clear()
}