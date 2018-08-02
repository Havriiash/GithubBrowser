package com.havriiash.dmitriy.spuilib.adapters.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.havriiash.dmitriy.spuilib.R
import com.havriiash.dmitriy.spuilib.adapters.itemlisteners.ItemClickListener
import com.havriiash.dmitriy.spuilib.adapters.viewholders.base.BaseViewHolder

class DefaultStringItemViewHolder(
        parentView: ViewGroup,
        vhClickListener: ItemClickListener<String>?
) : BaseViewHolder<String>(
        itemView = LayoutInflater.from(parentView.context).inflate(R.layout.default_string_item_view_holder, parentView, false),
        vhClickListener = vhClickListener) {

    private val titleView: TextView = itemView.findViewById(R.id.default_string_item_view_holder_title)
    private val subtitleView: TextView = itemView.findViewById(R.id.default_string_item_view_holder_subtitle)

    override fun setInfo(data: String) {
        super.setInfo(data)
        titleView.text = data
    }

    fun setSubtitle(subtitle: String?) {
        subtitleView.text = subtitle
        subtitleView.visibility = if (subtitle != null) View.VISIBLE else View.GONE
    }

}