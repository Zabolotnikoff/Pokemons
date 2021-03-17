package com.example.pokemons.data.network.web

import com.example.pokemons.Pokemon
import com.example.pokemons.domain.models.PokeGlobalQuantity
import com.example.pokemons.domain.models.PokeNameUrlList
import retrofit2.Call
import retrofit2.http.*

interface WebApi {

    @GET("?")
    fun getPokemonQuantity(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<PokeGlobalQuantity>

    @GET("?")
    fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<PokeNameUrlList>

    @GET("{id}/")
    fun getPokemonFromId(
        @Path("id") id: Int
    ): Call<Pokemon>

    @GET("{name}/")
    fun getPokemonFromName(
        @Path("name") name: String
    ): Call<Pokemon>

}