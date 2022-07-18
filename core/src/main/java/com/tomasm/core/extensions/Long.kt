package com.tomasm.core.extensions


fun Long.Companion.empty() = 0L

fun Long?.orEmpty(): Long = this ?: 0