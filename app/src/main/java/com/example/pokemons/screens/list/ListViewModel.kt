package com.example.pokemons.screens.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.pokemons.data.database.room.AppRoomDatabase
import com.example.pokemons.data.database.room.AppRoomRepository
import com.example.pokemons.utils.DATABASE_REPOSITORY

class ListViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application

    fun initDatabase(onSuccess: ()->Unit) {
        val dao = AppRoomDatabase.getInstance(context).getAppRoomDao()
        DATABASE_REPOSITORY = AppRoomRepository(dao)
        onSuccess()
    }
}