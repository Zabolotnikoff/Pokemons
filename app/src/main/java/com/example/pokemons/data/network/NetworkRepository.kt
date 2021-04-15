package com.example.pokemons.data.network

import com.example.pokemons.Pokemon
import com.example.pokemons.domain.models.PokeGlobalQuantity
import com.example.pokemons.domain.models.PokeNameUrlList

interface NetworkRepository {

    fun getPokemonFromName(name: String, callback: (Pokemon?, Int?) -> Unit): Unit

    fun getPokemonRandom(callback: (Pokemon?, Int?) -> Unit)
}