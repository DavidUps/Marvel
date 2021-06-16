package com.davidups.core.extensions

fun Boolean?.orEmpty(): Boolean = this ?: false
