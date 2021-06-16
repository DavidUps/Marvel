package com.davidups.core.extensions

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

fun ImageView.loadFromUrl(url: String) =
    Glide.with(this.context.applicationContext)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)

fun ImageView.loadFromUrlCircle(url: String) =
    Glide.with(this.context.applicationContext)
        .load(url)
        .apply(RequestOptions.circleCropTransform())
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)

fun ImageView.loadFromDrawable(drawable: Int) {

    Glide.with(this.context.applicationContext)
        .load(drawable)
        // .apply(options)
        .transition(DrawableTransitionOptions.withCrossFade())
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {

                return false
            }
        })
        .into(this)
}

fun ImageView.loadFromDrawable(drawable: Int, requestListener: RequestListener<Drawable>) {

    Glide.with(this.context.applicationContext)
        .load(drawable)
        // .apply(options)
        .transition(DrawableTransitionOptions.withCrossFade())
        .listener(requestListener)
        .into(this)
}

fun ImageView.loadFromBitmap(bitmap: Bitmap) {

    Glide.with(this.context.applicationContext)
        .load(bitmap)
        // .apply(options)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}
