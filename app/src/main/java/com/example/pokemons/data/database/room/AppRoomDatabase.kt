package com.example.pokemons.data.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pokemons.Pokemon

@Database(entities = [Pokemon::class], version = 1)
abstract class AppRoomDatabase() : RoomDatabase() {

    abstract fun getAppRoomDao(): AppRoomDao

    companion object {

        @Volatile
        private var database: AppRoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppRoomDatabase {

            if (database == null) {
                database = Room.databaseBuilder(
                    context,
                    AppRoomDatabase::class.java,
                    "poke_database"
                ).build()
            }

            return database as AppRoomDatabase
        }
    }
}