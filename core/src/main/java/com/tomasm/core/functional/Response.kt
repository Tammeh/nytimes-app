package com.tomasm.core.functional

import com.tomasm.core.exception.Failure

sealed class Response<out T : Any>
class Success<out T : Any>(val data: T) : Response<T>()

class Error(val failure: Failure) : Response<Nothing>()
