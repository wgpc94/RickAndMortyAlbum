package com.example.rickandmortyalbum.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.rickandmortyalbum.R
import com.example.rickandmortyalbum.model.Episode
import com.example.rickandmortyalbum.presenter.CharacterPresenter
import com.example.rickandmortyalbum.model.Character
import com.squareup.picasso.Picasso

class CharacterFragment : Fragment() {

    private lateinit var presenter: CharacterPresenter
    private lateinit var progressBar : ProgressBar
    private lateinit var contentCharacter: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = CharacterPresenter(this)
        return  inflater.inflate(R.layout.fragment_character_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.progress_bar_character)
        contentCharacter = view.findViewById(R.id.content_character)

        arguments.let {
            val characterId :Int = arguments?.getInt(Key.KEY_ID) ?: 0
            if (characterId != 0){
                presenter.findBy(characterId)
            }
        }
    }

    private fun bindCharacter(character: Character, episode: Episode) {
        val view = view
        if (view != null){
            Picasso.get().load(character.imageUrl).into(view.findViewById<ImageView>(R.id.img_character_detail))
            view.findViewById<TextView>(R.id.name_character_detail).text = character.name
            if (character.type == ""){
                view.findViewById<TextView>(R.id.response_type).text = character.species
            }else{
                view.findViewById<TextView>(R.id.response_type).text = character.type
            }
            view.findViewById<TextView>(R.id.response_gender).text = character.gender
            view.findViewById<TextView>(R.id.response_location).text = character.location.name
            view.findViewById<TextView>(R.id.name_episode).text = episode.name
            view.findViewById<TextView>(R.id.episode_date).text = episode.date
            view.findViewById<TextView>(R.id.episode_code).text = episode.episodeCode
        }
    }

    fun showProgress() {
        progressBar.visibility = View.VISIBLE
        contentCharacter.visibility = View.GONE
    }

    fun hiddenProgress(){
        contentCharacter.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    fun showFailure(msg : String){
        Toast.makeText(requireContext(),msg, Toast.LENGTH_SHORT).show()
    }

    fun onSuccess(character: Character, episode: Episode) {
        bindCharacter(character, episode)
    }

}
