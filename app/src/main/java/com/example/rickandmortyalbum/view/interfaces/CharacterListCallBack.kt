package com.example.rickandmortyalbum.view.interfaces

import com.example.rickandmortyalbum.model.Character
interface CharacterListCallBack {
    fun onSuccess(characterList: MutableList<Character>)
    fun onCompleted()
    fun onError(msg : String)
}