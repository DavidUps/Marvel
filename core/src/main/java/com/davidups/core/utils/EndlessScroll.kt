package com.davidups.core.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class EndlessScroll(
    private val recyclerView: RecyclerView,
    private val visibleThreshold: Int = -5,
    private val loadMore: () -> Unit
) : RecyclerView.OnScrollListener() {

    private var previousTotal = 1
    private var loading = true

    private fun changeRowByAccessibility() {
        if (this.recyclerView.scrollState != RecyclerView.SCROLL_STATE_IDLE) {
            return
        }
        loading()
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (this.recyclerView.scrollState == RecyclerView.SCROLL_STATE_IDLE) {
            return
        }
        loading()
    }

    private fun loading() {
        with(recyclerView) {
            val totalItemCount = layoutManager?.itemCount ?: 0
            val firstVisibleItem = when (layoutManager) {
                is StaggeredGridLayoutManager -> {
                    val lastVisibleItemPositions =
                        (layoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(
                            null
                        )
                    // get maximum element within the list
                    getLastVisibleItem(lastVisibleItemPositions)
                }
                is GridLayoutManager -> {
                    (layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                }
                is LinearLayoutManager -> {
                    (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                }
                else -> {
                    0
                }
            }

            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false
                    previousTotal = totalItemCount
                }
            }
            if (!loading && totalItemCount == firstVisibleItem + 1) {
                loadMore()
                loading = true
            }
        }
    }

    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }
}
