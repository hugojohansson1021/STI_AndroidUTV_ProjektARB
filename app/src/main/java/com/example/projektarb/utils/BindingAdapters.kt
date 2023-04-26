package com.example.projektarb.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("load")

// using glide to efficient load image
fun loadImage(imageView: ImageView, url: String?) {
    //loading image from respective url
    if (url != null) {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }
}





