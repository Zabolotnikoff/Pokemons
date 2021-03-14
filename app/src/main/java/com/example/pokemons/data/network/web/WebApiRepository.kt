package com.example.pokemons.data.network.web

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokemons.Pokemon
import com.example.pokemons.data.network.NetworkRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WebApiRepository: NetworkRepository {

    private var webApi: WebApi? = null

    init {
        webApi = WebApiClient.getWebApiClient().create(WebApi::class.java)
    }

    override fun getPokemonFromId(id: Int): MutableLiveData<Pokemon> {
        val result = MutableLiveData<Pokemon>()

        webApi?.getPokemonFromId(id)?.enqueue(object : Callback<Pokemon> {

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                result.value = null
            }

            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                when (response.code()) {
                    200 -> result.value = response.body()
                    400 -> result.value = null
                    else -> result.value = null
                }
            }
        })
        return result
    }

    override fun getPokemonFromName(name: String): MutableLiveData<Pokemon> {
        val result = MutableLiveData<Pokemon>()

        webApi?.getPokemonFromName(name)?.enqueue(object : Callback<Pokemon> {
            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                result.value = null
            }

            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                println("------------------name = ${name}----------------------------------------")
                println("------------------getPokemonFromName----------------------")
                println("------------------response.code = ${response.code()}----------------------")
                when (response.code()) {
                    200 -> result.value = response.body()
                    400 -> result.value = null
                    else -> result.value = null
                }
            }
        })
        return result
    }
}