package com.example.pokemons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.pokemons.data.database.room.AppRoomDatabase
import com.example.pokemons.data.database.room.AppRoomRepository
import com.example.pokemons.data.network.web.WebApiRepository
import com.example.pokemons.utils.ACTIVITY
import com.example.pokemons.utils.DATABASE_REPOSITORY
import com.example.pokemons.utils.NETWORK_REPOSITORY
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


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
        addFirstPoke()

    }

    private fun addFirstPoke() {

        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            DATABASE_REPOSITORY.insert(Pokemon(1, "Покемоша", 777, 111, 333)) { }
        }
//
//
//
//        CoroutineScope(Dispatchers.Main).launch {
//            delay(3000)
//            (activity as MainActivity).navController.navigate(R.id.action_startFragment_to_mainFragment)
//        }
//
//
//        viewModelScope.launch(Dispatchers.IO) {
//            DATABASE_REPOSITORY.insert(newPokemonLiveData?.value!!) {
//                callback()
//            }
//        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}