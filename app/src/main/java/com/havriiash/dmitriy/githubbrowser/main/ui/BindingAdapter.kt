package com.havriiash.dmitriy.githubbrowser.main.ui

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


@BindingAdapter("avatarUrl")
fun ImageView.setAvatarUrl(url: String?) {
    if (url != null) {
        Glide.with(this)
                .load(url)
                .apply(RequestOptions().circleCrop())
                .into(this)
    } else {
//        this.setImageDrawable() set image placeholder
    }
}