package com.davidups.marvel.platform

import android.content.Context
import com.davidups.marvel.extensions.networkInfo

class NetworkHandler
(private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnected
}
