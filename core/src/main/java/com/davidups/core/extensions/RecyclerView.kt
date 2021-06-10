package com.davidups.core.extensions

import androidx.recyclerview.widget.RecyclerView
import com.davidups.core.utils.EndlessScroll

fun RecyclerView.endless(visibleThreshold: Int, loadMore: () -> Unit) {
    this.addOnScrollListener(EndlessScroll(this, visibleThreshold, loadMore))
}
