package com.example.rickandmortyalbum.presenter

import com.example.rickandmortyalbum.data.RemoteDataSource
import com.example.rickandmortyalbum.model.Character
import com.example.rickandmortyalbum.model.Episode
import com.example.rickandmortyalbum.view.CharacterFragment
import com.example.rickandmortyalbum.view.interfaces.CharacterCallBack


class CharacterPresenter(
    private val view : CharacterFragment
) : CharacterCallBack {

    private val dataSource = RemoteDataSource()

    fun findBy(characterId: Int) {
        view.showProgress()
        dataSource.findBy(characterId, this)
    }

    override fun onCompleted() {
        view.hiddenProgress()
    }

    override fun onError(msg: String) {
        view.showFailure(msg)
    }

    override fun onSuccess(character: Character, episode: Episode) {
        view.onSuccess(character, episode)
    }
}