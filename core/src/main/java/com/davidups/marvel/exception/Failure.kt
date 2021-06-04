package com.davidups.marvel.exception

sealed class Failure {
    object NetworkConnection : Failure()
    class ServerError(private val errorCode: Int) : Failure()
    data class Throwable(val throwable: kotlin.Throwable?) : Failure()
    data class CustomError(val errorCode: Int, val errorMessage: String?) : Failure()
}
