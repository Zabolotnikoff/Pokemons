package com.example.pokemons.data.database.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pokemons.Pokemon

@Dao
interface AppRoomDao {

    @Query("SELECT * from poke_table")
    fun getAllPokemons(): LiveData<List<Pokemon>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun  insert(pokemon: Pokemon)

    @Delete
    suspend fun  delete(pokemon: Pokemon)

}