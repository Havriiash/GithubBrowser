package com.havriiash.dmitriy.spuilib.adapters.viewholders.base

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.ViewGroup

abstract class BaseProgressViewHolder<D>(
        parentView: ViewGroup,
        @LayoutRes private val progressLayoutRes: Int
): BaseViewHolder<D>(
        itemView = LayoutInflater.from(parentView.context).inflate(progressLayoutRes, parentView, false),
        vhClickListener = null
)