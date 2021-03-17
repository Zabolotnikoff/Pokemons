package com.example.pokemons.domain.models

import com.google.gson.annotations.SerializedName

data class PokeNameUrl(

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("url")
    var url: String? = null
)
