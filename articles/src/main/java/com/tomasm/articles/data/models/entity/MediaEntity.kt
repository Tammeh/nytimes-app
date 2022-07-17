package com.tomasm.articles.data.models.entity

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.tomasm.articles.data.models.data.MediaMetadata

@Entity
data class MediaEntity(
    @SerializedName("media-metadata")
    val mediaMetadata: MutableList<MediaMetadataEntity>?
)
