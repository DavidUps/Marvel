package com.davidups.marvel.platform

import android.content.Context

/**
 * Injectable class which handles context for access room database.
 */

class ContextHandler
(private val context: Context) {
    val appContext: Context get() = context.applicationContext
}