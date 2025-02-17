package com.shmulik.riversidemovie.util

import android.widget.ImageView
import coil.imageLoader
import coil.request.ImageRequest

fun ImageView.loadImage(url: String) {
    val request = ImageRequest.Builder(context)
        .data(url)
        .crossfade(true)
        .target(this)
        .build()

    context.imageLoader.enqueue(request)
}