package com.tomasm.core.extensions

fun Int.Companion.empty() = 0

fun Int?.orEmpty(): Int = this ?: 0