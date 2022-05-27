package com.example.rickandmortyalbum.model

data class Location(
    var id : Int,
    var name : String,
    var type : String,
    var dimension : String,
    var residents : ArrayList<String>,
    var url : String,
    var created : String
)