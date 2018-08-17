package com.havriiash.dmitriy.githubbrowser.main.ui

import android.databinding.BindingAdapter
import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
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

@BindingAdapter("commitFileName")
fun TextView.setCommitFileName(fileName: String) {
    val slashIndex = fileName.lastIndexOf("/")
    if (slashIndex != -1) {
        this.text = fileName.substring(slashIndex + 1)
    } else {
        this.text = fileName
    }
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