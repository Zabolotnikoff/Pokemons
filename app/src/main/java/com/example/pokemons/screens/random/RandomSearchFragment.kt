package com.example.pokemons.screens.random

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pokemons.Pokemon
import com.example.pokemons.R
import com.example.pokemons.utils.ACTIVITY
import kotlinx.android.synthetic.main.fragment_random_search.*

class RandomSearchFragment : Fragment() {

    private val randomViewModel: RandomViewModel by lazy {
        ViewModelProvider(this).get(RandomViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_random_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        randomViewModel.newPokemonLiveData?.observe(viewLifecycleOwner, Observer(::bindPokemon))

        randomFindButton.setOnClickListener {
            clearPokemonData()
            randomViewModel.getNewPokemonLiveData()
        }

        randomSaveButton.setOnClickListener {
            randomViewModel.insert() {
                ACTIVITY.navController.navigate(R.id.action_randomFindFragment_to_listFragment)
            }
        }
    }

    private fun clearPokemonData() {
        bindPokemon(Pokemon())
    }

    private fun bindPokemon(pokemon: Pokemon?) {
        randomItemNameTextView.text = let { pokemon?.name } ?: ""
        randomItemIdTextView.text = let { pokemon?.id?.toString() } ?: ""
        randomItemBaseExperienceTextView.text = let { pokemon?.baseExperience?.toString() } ?: ""
        randomItemHeightTextView.text = let { pokemon?.height?.toString() } ?: ""
        randomItemWeightTextView.text = let { pokemon?.weight?.toString() } ?: ""
    }

    override fun onDestroyView() {
        super.onDestroyView()
        randomViewModel.newPokemonLiveData?.removeObservers(viewLifecycleOwner)
    }
}