package com.havriiash.dmitriy.spuilib.adapters.viewholders

import android.view.ViewGroup
import com.havriiash.dmitriy.spuilib.R
import com.havriiash.dmitriy.spuilib.adapters.viewholders.base.BaseProgressViewHolder

class DefaultProgressViewHolder<D>(
        parentView: ViewGroup
) : BaseProgressViewHolder<D>(
        parentView = parentView,
        progressLayoutRes = R.layout.default_progress_view_holder)