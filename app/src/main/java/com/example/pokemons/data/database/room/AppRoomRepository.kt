package com.example.pokemons.data.database.room

import androidx.lifecycle.LiveData
import com.example.pokemons.Pokemon
import com.example.pokemons.data.database.DatabaseRepository

class AppRoomRepository(private val appRoomDao:AppRoomDao):DatabaseRepository {
    override val allPokemons: LiveData<List<Pokemon>>
        get() = appRoomDao.getAllPokemons()

    override suspend fun insert(pokemon: Pokemon, onSuccess: () -> Unit) {
        appRoomDao.insert(pokemon)
        onSuccess()
    }

    override suspend fun delete(pokemon: Pokemon, onSuccess: () -> Unit) {
        appRoomDao.delete(pokemon)
        onSuccess()
    }
}