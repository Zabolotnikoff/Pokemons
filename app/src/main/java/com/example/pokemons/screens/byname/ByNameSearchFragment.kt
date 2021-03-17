package com.example.pokemons.screens.byname

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.Fragment
import android.view.*
import com.example.pokemons.R
import com.example.pokemons.Pokemon
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
            clearPokemonView()
            byNameViewModel.getNewPokemonLiveData(editTextTextPersonName.text.toString())
        }

        byNameSaveButton.setOnClickListener {
            byNameViewModel.insert() {
                ACTIVITY.navController.navigate(R.id.action_nameFindFragment_to_listFragment)
            }
        }
    }

    private fun clearPokemonView() {
        bindPokemon(Pokemon())
    }

    private fun bindPokemon(pokemon: Pokemon?) {
        byNameItemNameTextView.text = let { pokemon?.name } ?: ""
        byNameItemIdTextView.text = let { pokemon?.id?.toString() } ?: ""
        byNameItemBaseExperienceTextView.text = let { pokemon?.baseExperience?.toString() } ?: ""
        byNameItemHeightTextView.text = let { pokemon?.height?.toString() } ?: ""
        byNameItemWeightTextView.text = let { pokemon?.weight?.toString() } ?: ""
    }

    override fun onDestroyView() {
        super.onDestroyView()
        byNameViewModel.newPokemonLiveData?.removeObservers(viewLifecycleOwner)
    }}