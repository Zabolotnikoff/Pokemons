package com.example.pokemons.data.network.web

import com.example.pokemons.Pokemon
import com.example.pokemons.model.PokeGlobalQuantity
import retrofit2.Call
import retrofit2.http.*

interface WebApi {

    @GET("?limit=1")
    fun getPokemonQuantity(): Call<PokeGlobalQuantity>

    @GET("{id}/")
    fun getPokemonFromId(@Path("id") id: Int): Call<Pokemon>

    @GET("{name}/")
    fun getPokemonFromName(@Path("name") name: String): Call<Pokemon>

}