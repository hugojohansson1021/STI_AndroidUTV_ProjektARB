package com.example.projektarb.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("load")
fun loadImage(view: ImageView, url: String){

// Using glide to load url image and putting it in view
    Glide.with(view).load(url).into(view)

}