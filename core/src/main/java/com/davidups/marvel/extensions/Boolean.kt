package com.davidups.marvel.extensions

inline fun Boolean?.orEmpty(): Boolean = this ?: false
