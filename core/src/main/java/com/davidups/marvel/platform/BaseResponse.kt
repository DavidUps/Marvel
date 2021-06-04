package com.davidups.marvel.platform

data class BaseResponse(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val data: Any,
    val etag: String
)
