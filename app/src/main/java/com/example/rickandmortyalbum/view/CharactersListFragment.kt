package com.example.rickandmortyalbum.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyalbum.R
import com.example.rickandmortyalbum.model.Character
import com.example.rickandmortyalbum.presenter.CharacterListPresenter
import com.example.rickandmortyalbum.view.adapters.CharacterListAdapter
import com.example.rickandmortyalbum.view.adapters.PageAdapter
import com.example.rickandmortyalbum.view.interfaces.OnClickCharacter


class CharactersListFragment : Fragment(), OnClickCharacter{
    private lateinit var pagesAdapter : PageAdapter
    private lateinit var characterAdapter : CharacterListAdapter
    private lateinit var rvCharacter : RecyclerView
    private lateinit var rvPages : RecyclerView
    private lateinit var progressBar : ProgressBar
    private lateinit var presenter : CharacterListPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        pagesAdapter = PageAdapter(this)
        characterAdapter = CharacterListAdapter(this)
        presenter = CharacterListPresenter(this)
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view){
            rvCharacter = findViewById(R.id.characters_list)
            rvCharacter.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            rvCharacter.adapter = characterAdapter

            rvPages = findViewById(R.id.pages_list)
            rvPages.layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL, false)
            rvPages.adapter = pagesAdapter

            progressBar = findViewById(R.id.progress_bar)
        }
        presenter.findCharacterList(null)
    }

    fun onSuccess(characterList : MutableList<Character>, countList : MutableList<Int>){
        characterAdapter.setList(characterList)
        pagesAdapter.setList(countList)
    }

    fun showProgress(){
        rvCharacter.visibility = View.GONE
        rvPages.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    fun hiddenProgress(){
        progressBar.visibility = View.GONE
        rvCharacter.visibility = View.VISIBLE
        rvPages.visibility = View.VISIBLE
    }

    fun showFailure(msg : String){
        progressBar.visibility = View.GONE
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    override fun onClickCharacter(characterId: Int) {
        val fragment = CharacterFragment()
        val value = Bundle()
        value.putInt(Key.KEY_ID, characterId)
        fragment.arguments = value
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fill,fragment)
                .addToBackStack(null)
            commit()
        }
    }

    override fun onClickPages(pageId: Int) {
        presenter.findCharacterList(pageId)
    }

}