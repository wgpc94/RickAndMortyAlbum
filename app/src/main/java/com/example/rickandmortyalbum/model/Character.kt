package com.example.rickandmortyalbum.model

data class Character(
    var id: Int,
    var name: String,
    var status: String,
    var species: String,
    var type: String,
    var gender: String,
    var origin: Origin?,
    var location: Location?,
    var imageUrl: String?,
    var episodeUrl: ArrayList<String>?,
    var characterUrl: String?,
    var created: String?
)