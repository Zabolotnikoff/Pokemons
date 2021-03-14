package com.example.pokemons.screens.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.pokemons.R

class ListFragment : Fragment() {

    private lateinit var listViewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)

    }

    override fun onStart() {
        super.onStart()
        initial()
    }

    private fun initial() {
        listViewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        listViewModel.initDatabase { }
    }

}