package com.example.pokemons.screens.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemons.Pokemon
import com.example.pokemons.R
import kotlinx.android.synthetic.main.list_item.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var pokeList: MutableList<Pokemon> = mutableListOf()

    fun setPokeList(newPokeList: List<Pokemon>) {
        pokeList.clear()
        pokeList.addAll(newPokeList)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokeList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pokeList[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(pokemon: Pokemon) {
            itemView.itemNameTextView.text = pokemon.name
            itemView.itemIdTextView.text = pokemon.id.toString()
            itemView.itemBaseExperienceTextView.text = pokemon.baseExperience.toString()
            itemView.itemHeightTextView.text = pokemon.height.toString()
            itemView.itemWeightTextView.text = pokemon.weight.toString()
        }
    }
}