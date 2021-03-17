package com.example.pokemons.domain.models

import com.google.gson.annotations.SerializedName

data class PokeNameUrlList (

    @SerializedName("results")
    var results: List<PokeNameUrl>
)