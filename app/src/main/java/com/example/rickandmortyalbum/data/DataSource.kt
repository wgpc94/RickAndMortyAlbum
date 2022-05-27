package com.example.rickandmortyalbum.data

import android.os.Handler
import android.os.Looper
import com.example.rickandmortyalbum.model.Character
import com.example.rickandmortyalbum.model.Episode
import com.example.rickandmortyalbum.presenter.CharacterPresenter
import com.example.rickandmortyalbum.view.interfaces.CharacterListCallBack

class DataSource {
    fun findCharacterList(page: Int?, callBack: CharacterListCallBack) {
        Handler(Looper.getMainLooper()).postDelayed({
            var characterList = mutableListOf<Character>()
            characterList.add(
                Character(
                    1,"Maik","dead","unknown","","male",null, null,"null",null,null,null),
                )
            characterList.add(
                Character(
                    2,"Alff","alive","unknown","","male",null, null,"null",null,null,null),
            )
            characterList.add(
                Character(
                    3,"Maicon","dead","unknown","","male",null, null,"null",null,null,null),
            )
            characterList.add(
                Character(
                    4,"Jonas","alive","unknown","","male",null, null,"null",null,null,null),
            )
            callBack.onSuccess(characterList)
            callBack.onCompleted()
        }, 1000)
    }

    fun findBy(characterId: Int, characterPresenter: CharacterPresenter) {
        characterPresenter.onSuccess(
            Character(
                4,"Jonas","alive","unknown","","male",null, null,"null",null,null,null)
        , Episode("the lost rick", "30/02/0000","Ops")
        )
        characterPresenter.onCompleted()
    }
}