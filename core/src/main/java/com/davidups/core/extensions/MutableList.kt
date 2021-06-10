package com.davidups.core.extensions

inline fun <T> MutableList<T>?.orEmpty(): MutableList<T> = this ?: mutableListOf()
