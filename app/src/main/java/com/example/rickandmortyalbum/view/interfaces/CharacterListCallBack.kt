package com.example.rickandmortyalbum.view.interfaces

import com.example.rickandmortyalbum.model.InfoAndResult

interface CharacterListCallBack {
    fun onSuccess(iAR : InfoAndResult)
    fun onCompleted()
    fun onError(msg : String)
}