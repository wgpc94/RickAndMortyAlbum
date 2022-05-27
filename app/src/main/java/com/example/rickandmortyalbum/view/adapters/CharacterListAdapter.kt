package com.example.rickandmortyalbum.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyalbum.R
import com.example.rickandmortyalbum.model.Character
import com.example.rickandmortyalbum.view.interfaces.OnClickCharacter

class CharacterListAdapter(
    private val onClickCharacter: OnClickCharacter
) : RecyclerView.Adapter<CharacterListAdapter.CharacterListViewHolder>() {

    private var values = mutableListOf<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        return CharacterListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_character,parent, false))
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        holder.bind(values[position])
    }

    override fun getItemCount(): Int {
       return values.size
    }

    fun setList(characterList: MutableList<Character>) {
        values.clear()
        values = characterList
        notifyDataSetChanged()
    }


    inner class CharacterListViewHolder(private val view : View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Character) {

            view.findViewById<TextView>(R.id.name_character).text = item.name
            view.findViewById<TextView>(R.id.response_status_character).text = item.status
            view.findViewById<TextView>(R.id.response_species_character).text = item.species
            view.findViewById<TextView>(R.id.response_first_location).text = item.origin?.name
            view.findViewById<TextView>(R.id.response_last_location).text = item.location?.name
            view.setOnClickListener {
                onClickCharacter.onClickCharacter(item.id)
            }
        }
    }
}
