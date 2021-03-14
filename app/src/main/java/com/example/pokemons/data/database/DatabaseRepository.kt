package com.example.pokemons.data.database

import androidx.lifecycle.LiveData
import com.example.pokemons.Pokemon

interface DatabaseRepository {

    val allPokemons: LiveData<List<Pokemon>>

    suspend fun insert(pokemon: Pokemon, onSuccess: () -> Unit)

    suspend fun delete(pokemon: Pokemon, onSuccess: () -> Unit)
}