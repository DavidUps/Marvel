package com.davidups.core.functional

import com.davidups.core.exception.Failure
import com.davidups.core.extensions.empty

sealed class State<out T : Any>

class Success<out T : Any>(val data: T) : State<T>()

class Error(
    val failure: Failure
) : State<Nothing>()

fun error() = Error(Failure.ServerError(Int.empty()))
fun error(code: Int) = Error(Failure.ServerError(code))
