package com.example.pokemons.screens.byname

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokemons.Pokemon
import com.example.pokemons.utils.DATABASE_REPOSITORY
import com.example.pokemons.utils.NETWORK_REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ByNameViewModel(application: Application) : AndroidViewModel(application) {

    var newPokemonLiveData: MutableLiveData<Pokemon>? = null

    init {
        newPokemonLiveData = MutableLiveData()
    }

    fun getNewPokemonLiveData(name: String) {
        NETWORK_REPOSITORY.getPokemonFromName(name) { result, _ -> //что делать с кодом ошибки пока не решил
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