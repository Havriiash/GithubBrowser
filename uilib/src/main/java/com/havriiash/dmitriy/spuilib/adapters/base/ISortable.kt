package com.havriiash.dmitriy.spuilib.adapters.base

interface ISortable<D> {
    fun sort(unsortedList: List<D>): List<D>
}