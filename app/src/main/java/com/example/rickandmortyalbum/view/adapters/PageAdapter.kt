package com.example.rickandmortyalbum.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyalbum.R
import com.example.rickandmortyalbum.view.interfaces.OnClickCharacter

class PageAdapter(
    private val onClickCharacter : OnClickCharacter
) : RecyclerView.Adapter<PageAdapter.PageViewHolder>(){
    private var values = mutableListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        return PageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items_page, parent, false))
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        holder.bind(values[position])
    }

    override fun getItemCount(): Int {
        return values.size
    }

    fun setList(countList: MutableList<Int>) {
        values.clear()
        values = countList
        notifyDataSetChanged()
    }



    inner class PageViewHolder(private val view : View) : RecyclerView.ViewHolder(view) {
        fun bind(page: Int) {
            val pageNumber: TextView = itemView.findViewById(R.id.count)
            pageNumber.text = page.toString()
            view.setOnClickListener {
                onClickCharacter.onClickPages(page)
            }
        }
    }
}
