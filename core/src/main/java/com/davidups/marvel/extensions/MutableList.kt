package com.davidups.marvel.extensions

inline fun <T> MutableList<T>?.orEmpty(): MutableList<T> = this ?: mutableListOf()