package com.davidups.core.extensions

inline fun Boolean?.orEmpty(): Boolean = this ?: false
