package com.example.pokemons.screens.random

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokemons.Pokemon
import com.example.pokemons.domain.models.PokeNameUrl
import com.example.pokemons.utils.DATABASE_REPOSITORY
import com.example.pokemons.utils.NETWORK_REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RandomViewModel(application: Application) : AndroidViewModel(application) {

    private var pokemonQuantity: Int? = null
    private var listOfPokeNameUrl: List<PokeNameUrl>? = null

    var newPokemonLiveData: MutableLiveData<Pokemon>? = null

    init {
//        setPokeNameUrlList()
        newPokemonLiveData = MutableLiveData()
    }

    //что делать с кодом ошибки пока не решил
    fun getNewPokemonLiveData() {
        NETWORK_REPOSITORY.getPokemonRandom() { result, _ ->
            newPokemonLiveData?.postValue(result)
        }
    }

    fun insert(callback: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            DATABASE_REPOSITORY.insert(newPokemonLiveData?.value!!) {
                callback()
            }
        }
    }
}