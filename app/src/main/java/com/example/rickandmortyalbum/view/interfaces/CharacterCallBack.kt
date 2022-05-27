package com.example.rickandmortyalbum.view.interfaces

import com.example.rickandmortyalbum.model.Character
import com.example.rickandmortyalbum.model.Episode

interface CharacterCallBack {
    fun onCompleted()
    fun onError(msg :String)
    fun onSuccess(character : Character, episode: Episode)
}