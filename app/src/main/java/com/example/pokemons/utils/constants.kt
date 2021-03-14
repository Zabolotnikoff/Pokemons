package com.example.pokemons.utils

import com.example.pokemons.MainActivity
import com.example.pokemons.data.database.DatabaseRepository
import com.example.pokemons.data.network.NetworkRepository

lateinit var ACTIVITY: MainActivity
lateinit var NETWORK_REPOSITORY: NetworkRepository
lateinit var DATABASE_REPOSITORY: DatabaseRepository
const val BASEURL = "https://pokeapi.co/api/v2/pokemon/"