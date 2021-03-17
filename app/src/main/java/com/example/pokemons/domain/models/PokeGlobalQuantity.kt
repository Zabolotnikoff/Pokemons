package com.example.pokemons.domain.models

import com.google.gson.annotations.SerializedName

data class PokeGlobalQuantity(
    @SerializedName("count")
    var quantity: Int? = null
)