package com.example.pokemons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.pokemons.data.database.room.AppRoomDatabase
import com.example.pokemons.data.database.room.AppRoomRepository
import com.example.pokemons.data.network.web.WebApiRepository
import com.example.pokemons.utils.ACTIVITY
import com.example.pokemons.utils.DATABASE_REPOSITORY
import com.example.pokemons.utils.NETWORK_REPOSITORY
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        applicationInitial()
        navController = Navigation.findNavController(this, R.id.navHostFragment)
        setSupportActionBar(toolbar)
        title = getString(R.string.toolbar_title)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_action_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun applicationInitial() {
        ACTIVITY = this
        NETWORK_REPOSITORY = WebApiRepository()
        DATABASE_REPOSITORY =
            AppRoomRepository(AppRoomDatabase.getInstance(ACTIVITY).getAppRoomDao())
    }
}