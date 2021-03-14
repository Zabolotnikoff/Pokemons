package com.example.pokemons.screens.byname

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
import kotlinx.android.synthetic.main.fragment_by_name_search.*

class ByNameSearchFragment : Fragment() {

    private val byNameViewModel: ByNameViewModel by lazy {
        ViewModelProvider(this).get(ByNameViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_by_name_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        byNameViewModel.newPokemonLiveData?.observe(viewLifecycleOwner, Observer(::bindPokemon))

        byNameFindButton.setOnClickListener {
            byNameViewModel.getNewPokemonLiveData(editTextTextPersonName.text.toString())
        }

        byNameSaveButton.setOnClickListener {
            byNameViewModel.insert() {
                ACTIVITY.navController.navigate(R.id.action_nameFindFragment_to_listFragment)
            }
        }

///// ----------Кнопки для отладки--------------------------------------------------------
        buttonHardNull.setOnClickListener {
            byNameViewModel.newPokemonLiveData?.value.let {
                it?.name = "Null"
                it?.id = 0
                it?.baseExperience = 0
                it?.height = 0
                it?.weight = 0
            }
        }

        buttonHardBind.setOnClickListener {
            byNameViewModel.newPokemonLiveData?.value?.let (::bindPokemon)
        }
    }

    private fun bindPokemon(pokemon: Pokemon) {
        byNameItemNameTextView.text = pokemon.name
        byNameItemIdTextView.text = pokemon.id.toString()
        byNameItemBaseExperienceTextView.text = pokemon.baseExperience.toString()
        byNameItemHeightTextView.text = pokemon.height.toString()
        byNameItemWeightTextView.text = pokemon.weight.toString()
    }

}