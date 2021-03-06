package com.example.pokemons.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokemons.R
import com.example.pokemons.utils.ACTIVITY
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onStart() {
        super.onStart()

        byNameSearchButton.setOnClickListener {
            ACTIVITY.navController.navigate(R.id.action_mainFragment_to_nameFindFragment)
        }

        randomSearchButton.setOnClickListener {
            ACTIVITY.navController.navigate(R.id.action_mainFragment_to_randomFindFragment)
        }

        toListButton.setOnClickListener {
            ACTIVITY.navController.navigate(R.id.action_mainFragment_to_listFragment)
        }
    }












}


