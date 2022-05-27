package com.example.rickandmortyalbum.model

import com.google.gson.annotations.SerializedName

data class Episode(
    val name: String,
    @SerializedName("air_date") val date: String,
    @SerializedName("episode") val episodeCode: String
)