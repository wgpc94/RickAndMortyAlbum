package com.example.rickandmortyalbum.model

import com.google.gson.annotations.SerializedName

data class Character(
    var id: Int,
    var name: String,
    var status: String,
    var species: String,
    var type: String,
    var gender: String,
    var origin: Origin,
    var location : Location,
    @SerializedName("image")  var imageUrl : String,
    @SerializedName("episode") var episodeUrl : ArrayList<String>,
    @SerializedName("url") var characterUrl : String,
    var created: String?
)