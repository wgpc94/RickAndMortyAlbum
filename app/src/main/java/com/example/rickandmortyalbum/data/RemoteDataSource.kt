package com.example.rickandmortyalbum.data

import com.example.rickandmortyalbum.model.Character
import com.example.rickandmortyalbum.model.Episode
import com.example.rickandmortyalbum.model.InfoAndResult
import com.example.rickandmortyalbum.view.interfaces.CharacterCallBack
import com.example.rickandmortyalbum.view.interfaces.CharacterListCallBack
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    fun findCharacterList(page: Int?, callBack: CharacterListCallBack) {
       HTTPClient.retrofit()
           .create(TheRickAndMortyApi::class.java)
           .findPageBy(page)
           .enqueue(object : Callback<InfoAndResult>{
               override fun onResponse(
                   call: Call<InfoAndResult>,
                   response: Response<InfoAndResult>
               ) {
                   if (response.isSuccessful){
                       val responseInfoAndResults = response.body()
                       if (responseInfoAndResults != null){
                           callBack.onSuccess(responseInfoAndResults)
                       }else{
                           callBack.onError(response.errorBody()?.string() ?: "unknown error")
                       }
                   }
                   callBack.onCompleted()
               }

               override fun onFailure(call: Call<InfoAndResult>, t: Throwable) {
                   callBack.onError(t.message ?: "Service error")
               }

           })
    }

    fun findBy(characterId : Int, callBack: CharacterCallBack){
        HTTPClient.retrofit()
            .create(TheRickAndMortyApi::class.java)
            .findBy(characterId)
            .enqueue(object : Callback<Character> {
                override fun onResponse(call: Call<Character>, response: Response<Character>) {
                    if (response.isSuccessful){
                        val character = response.body()
                        if (character != null){
                            val episodeUrl = character.episodeUrl.first().split("/")
                            val episodeId = episodeUrl.last()
                            findEpisode(character, episodeId, callBack)
                        }else{
                            callBack.onError(response.errorBody()?.string() ?: "Unknown error")
                        }
                    }
                }

                override fun onFailure(call: Call<Character>, t: Throwable) {
                    callBack.onCompleted()
                    callBack.onError(t.message ?: "Service Error")
                }
            })
    }


    fun findEpisode(character: Character, first: String, callBack: CharacterCallBack){
        lateinit var episode : Episode
        HTTPClient.retrofit().create(TheRickAndMortyApi::class.java)
            .findEpisode(first)
            .enqueue(object : Callback<Episode>{
                override fun onResponse(call: Call<Episode>, response: Response<Episode>) {
                    if (response.isSuccessful){
                        val responseEpisode =  response.body()
                        if (responseEpisode != null){
                            episode = responseEpisode
                            callBack.onSuccess(character, episode)
                        }
                    }else{
                        callBack.onError(response.errorBody()?.string() ?: "Unknown error")
                    }
                    callBack.onCompleted()
                }

                override fun onFailure(call: Call<Episode>, t: Throwable) {
                    callBack.onError(t.message ?: "Service error")
                    callBack.onCompleted()
                }
            })
    }
}