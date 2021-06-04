package com.davidups.marvel.extensions

import androidx.recyclerview.widget.RecyclerView
import com.davidups.marvel.utils.EndlessScroll

fun RecyclerView.endless(visibleThreshold: Int, loadMore: () -> Unit) {
    this.addOnScrollListener(EndlessScroll(this, visibleThreshold, loadMore))
}
