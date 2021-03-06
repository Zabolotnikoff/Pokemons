package com.example.pokemons.data.network.web

import com.example.pokemons.utils.BASEURL
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class WebApiClient {

    companion object {

        @Volatile
        private var retrofit: Retrofit? = null

        @Synchronized
        fun getInstance(): Retrofit? {

            if (retrofit == null) {
                val gson = GsonBuilder()
                    .setLenient()
                    .create()
                val okHttpClient = OkHttpClient.Builder()
                    .readTimeout(2, TimeUnit.SECONDS)
                    .connectTimeout(2, TimeUnit.SECONDS)
                    .build()

                retrofit = Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }

            return retrofit
        }
    }
}