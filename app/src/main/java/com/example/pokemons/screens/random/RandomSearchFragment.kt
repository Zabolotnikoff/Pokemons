package com.example.pokemons.screens.random

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.pokemons.MainActivity
import com.example.pokemons.R
import kotlinx.android.synthetic.main.fragment_random_search.*

class RandomSearchFragment : Fragment() {

    private lateinit var randomViewModel: RandomViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_random_search, container, false)
    }

    override fun onStart() {
        super.onStart()
        initial()
        randomSaveButton.setOnClickListener {
            (activity as MainActivity).navController.navigate(R.id.action_randomFindFragment_to_listFragment)
        }
    }

    private fun initial() {
        randomViewModel = ViewModelProvider(this).get(RandomViewModel::class.java)
    }
}
