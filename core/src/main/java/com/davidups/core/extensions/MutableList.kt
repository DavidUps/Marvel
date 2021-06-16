package com.davidups.core.extensions

fun <T> MutableList<T>?.orEmpty(): MutableList<T> = this ?: mutableListOf()
