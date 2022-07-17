package com.tomasm.articles.data.models.entity

import com.google.gson.annotations.SerializedName
import com.tomasm.articles.data.models.data.MediaMetadata

data class MediaEntity(
    @SerializedName("media-metadata")
    val mediaMetadata: MutableList<MediaMetadataEntity>?
)
