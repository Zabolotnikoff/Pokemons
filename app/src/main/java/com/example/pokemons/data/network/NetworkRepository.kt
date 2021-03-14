package com.example.pokemons.data.network

import androidx.lifecycle.LiveData
import com.example.pokemons.Pokemon
import com.example.pokemons.model.PokeGlobalQuantity

interface NetworkRepository {

    fun getPokemonFromId(id: Int):  LiveData<Pokemon>
    fun getPokemonFromName(name: String):  LiveData<Pokemon>
}