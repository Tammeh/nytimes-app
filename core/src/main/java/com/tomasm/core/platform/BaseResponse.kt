package com.tomasm.core.platform

data class BaseResponse<T : Any> (
    val status: String,
    val copyright: String,
    val num_results: Int,
    val results: T
)