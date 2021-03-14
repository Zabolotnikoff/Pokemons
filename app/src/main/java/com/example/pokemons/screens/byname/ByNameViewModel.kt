package com.example.pokemons.screens.byname

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokemons.Pokemon
import com.example.pokemons.data.database.room.AppRoomDatabase
import com.example.pokemons.data.database.room.AppRoomRepository
import com.example.pokemons.data.network.web.WebApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ByNameViewModel(application: Application) : AndroidViewModel(application) {
    val context = application

    private var networkRepository: WebApiRepository? = null
    private var databaseRepository: AppRoomRepository? = null

    var newPokemonLiveData: MutableLiveData<Pokemon>? = null

    init {
        networkRepository = WebApiRepository()
        databaseRepository = AppRoomRepository(AppRoomDatabase.getInstance(context).getAppRoomDao())

        newPokemonLiveData = MutableLiveData()
    }

    fun getNewPokemonLiveData(name: String) {
        newPokemonLiveData = networkRepository?.getPokemonFromName(name)
    }

    fun insert(onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository?.insert(newPokemonLiveData?.value!!) {
                onSuccess()
            }
        }
    }
}