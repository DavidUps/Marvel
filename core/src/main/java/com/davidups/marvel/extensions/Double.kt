package com.davidups.marvel.extensions

fun Double.Companion.empty() = 0.0

inline fun Double?.orEmpty(): Double = this ?: 0.0
