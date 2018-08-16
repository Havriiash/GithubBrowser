package com.havriiash.dmitriy.githubbrowser.main.ui

import android.databinding.BindingAdapter
import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.havriiash.dmitriy.githubbrowser.R


@BindingAdapter("avatarUrl")
fun ImageView.setAvatarUrl(url: String?) {
    setImageFromUrl(url, this, true)
}

@BindingAdapter("imageSrc")
fun ImageView.setImageSrc(url: String?) {
    setImageFromUrl(url, this, false)
}

private fun setImageFromUrl(url: String?, imageView: ImageView, isCircled: Boolean) {
    if (!TextUtils.isEmpty(url)) {
        if (isCircled) {
            Glide.with(imageView)
                    .load(url)
                    .apply(RequestOptions().circleCrop())
                    .into(imageView)
        } else {
            Glide.with(imageView)
                    .load(url)
                    .into(imageView)
        }
    } else {
        imageView.setImageResource(R.mipmap.logo_big)
    }
}