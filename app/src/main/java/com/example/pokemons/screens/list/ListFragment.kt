package com.example.pokemons.screens.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemons.Pokemon
import com.example.pokemons.R
import com.example.pokemons.screens.byname.ByNameViewModel
import com.example.pokemons.utils.ACTIVITY
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private val listViewModel: ListViewModel by lazy {
        ViewModelProvider(this).get(ListViewModel::class.java)
    }
    private var listAdapter = ListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listViewModel.allPoke.observe(viewLifecycleOwner, Observer(::setPokeList))

        val layoutManager =
            LinearLayoutManager(ACTIVITY, LinearLayoutManager.VERTICAL, false)

        pokeRecyclerView.adapter = listAdapter
        pokeRecyclerView.layoutManager = layoutManager
    }

    private fun setPokeList(pokeList: List<Pokemon>) {
        listAdapter.setPokeList(pokeList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        listViewModel.allPoke.removeObservers(viewLifecycleOwner)
        pokeRecyclerView.adapter = null
    }
}


