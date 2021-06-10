package com.davidups.core.platform

data class BaseResponse<T : Any>(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val data: T,
    val etag: String
)
