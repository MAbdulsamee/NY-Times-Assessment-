package com.example.nytimes.data.models

import com.google.gson.annotations.SerializedName

data class Media (
    @SerializedName("media-metadata")
    val mediaMetadata: List<MediaMetadata>
)