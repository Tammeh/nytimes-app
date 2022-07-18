package com.tomasm.core.utils

data class FilterItem (
    val displayName: String,
    val serviceName: String
){
    override fun toString(): String {
        return displayName
    }
}