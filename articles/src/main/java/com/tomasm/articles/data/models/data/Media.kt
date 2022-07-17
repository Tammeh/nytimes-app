package com.tomasm.articles.data.models.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Media(
    val mediaMetadata: MutableList<MediaMetadata>?
) : Parcelable
