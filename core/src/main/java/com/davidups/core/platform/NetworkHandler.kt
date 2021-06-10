package com.davidups.core.platform

import android.content.Context
import com.davidups.core.extensions.networkInfo

class NetworkHandler
(private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnected
}
