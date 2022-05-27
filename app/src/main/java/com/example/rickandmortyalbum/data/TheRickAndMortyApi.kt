package com.example.rickandmortyalbum.data

import com.example.rickandmortyalbum.model.Character
import com.example.rickandmortyalbum.model.Episode
import com.example.rickandmortyalbum.model.InfoAndResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheRickAndMortyApi {
    @GET("character/")
    fun findPageBy(@Query("page") pageId : Int? ): Call<InfoAndResult>

    @GET("character/{id}")
    fun findBy(@Path("id") id: Int): Call<Character>

    @GET("episode/{id}")
    fun findEpisode(@Path("id") url : String) : Call<Episode>
}