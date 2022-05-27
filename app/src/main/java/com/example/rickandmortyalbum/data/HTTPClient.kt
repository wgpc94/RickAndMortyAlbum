package com.example.rickandmortyalbum.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HTTPClient {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    fun retrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}