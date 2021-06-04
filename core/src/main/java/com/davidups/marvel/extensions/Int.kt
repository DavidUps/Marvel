package com.davidups.marvel.extensions

fun Int.Companion.empty() = 0

inline fun Int?.orEmpty(): Int = this ?: 0
