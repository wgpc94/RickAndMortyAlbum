package com.example.rickandmortyalbum.presenter


import com.example.rickandmortyalbum.data.RemoteDataSource
import com.example.rickandmortyalbum.model.InfoAndResult
import com.example.rickandmortyalbum.view.CharactersListFragment
import com.example.rickandmortyalbum.view.interfaces.CharacterListCallBack

class CharacterListPresenter(
    private val view : CharactersListFragment
): CharacterListCallBack{

    private val dataSource = RemoteDataSource()

    fun findCharacterList(page : Int?){
        view.showProgress()
        dataSource.findCharacterList(page, this)
    }


    override fun onSuccess(iAR: InfoAndResult) {
        val pages = mutableListOf<Int>()
        for (i in 1..iAR.info.pages){
            pages.add(i)
        }
        view.onSuccess(iAR.results, pages)
    }

    override fun onCompleted() {
        view.hiddenProgress()
    }

    override fun onError(msg: String) {
        view.showFailure(msg)
    }

}