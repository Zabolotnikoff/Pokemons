package com.example.pokemons.data.network.web

import com.example.pokemons.Pokemon
import com.example.pokemons.data.network.NetworkRepository
import com.example.pokemons.domain.models.PokeGlobalQuantity
import com.example.pokemons.domain.models.PokeNameUrl
import com.example.pokemons.domain.models.PokeNameUrlList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WebApiRepository : NetworkRepository {

    private var webApi: WebApi? = null
    private var quantity: Int? = null
    private var listOfNameUrl: List<PokeNameUrl>? = null

    init {
        webApi = WebApiClient.getInstance()?.create(WebApi::class.java)

        getPokemonQuantity() { it1 ->
            getPokemonList(it1?.quantity) { it2 ->
                listOfNameUrl = it2?.results
                quantity = listOfNameUrl?.size
            }
        }
    }

    private fun getPokemonQuantity(callback: (result: PokeGlobalQuantity?) -> Unit): Unit {

        webApi?.getPokemonQuantity(offset = 0, limit = 1)?.enqueue(
            object : Callback<PokeGlobalQuantity> {
                override fun onFailure(call: Call<PokeGlobalQuantity>, t: Throwable) {
                    callback(null)
                }

                override fun onResponse(
                    call: Call<PokeGlobalQuantity>,
                    response: Response<PokeGlobalQuantity>
                ) {
                    callback(response.body())
                }
            })
    }

    private fun getPokemonList(count: Int?, callback: (result: PokeNameUrlList?) -> Unit): Unit {

        webApi?.getPokemonList(offset = 0, limit = count ?: 0)?.enqueue(
            object : Callback<PokeNameUrlList> {
                override fun onFailure(call: Call<PokeNameUrlList>, t: Throwable) {
                    callback(null)
                }

                override fun onResponse(
                    call: Call<PokeNameUrlList>,
                    response: Response<PokeNameUrlList>
                ) {
                    callback(response.body())
                }
            })
    }

    override fun getPokemonFromName(
        name: String,
        callback: (result: Pokemon?, code: Int?) -> Unit
    ): Unit {
        webApi?.getPokemonFromName(name)?.enqueue(

            object : Callback<Pokemon> {

                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    callback(null, null)
                }

                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    callback(response.body(), response.code())
                }
            })
    }

    override fun getPokemonRandom(callback: (Pokemon?, Int?) -> Unit) {
        val name = getRandomPokeName()
        name?.let {
            getPokemonFromName(it) { result, code ->
                callback(result, code)
            }
        }
        callback(null, null)
    }

    private fun getRandomPokeName(): String? {
        val quantity: Int? = quantity
        val currentNumber = rndNumber(quantity)
        return listOfNameUrl?.get(currentNumber)?.name
    }

    private fun rndNumber(maxNumber: Int?): Int =
        maxNumber?.let { (Math.random() * it).toInt() } ?: 0
}
