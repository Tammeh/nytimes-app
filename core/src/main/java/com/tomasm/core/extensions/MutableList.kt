package com.tomasm.core.extensions

fun <T> MutableList<T>?.orEmpty(): MutableList<T> = this ?: mutableListOf()