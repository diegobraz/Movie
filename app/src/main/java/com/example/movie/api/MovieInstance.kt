package com.example.movie.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieInstance {

    private val retrofit by lazy {

           Retrofit.Builder()
               .baseUrl("https://private-b34167-rvmarvel.apiary-mock.com/")
               .addConverterFactory(GsonConverterFactory.create())
               .build()
       }
    val api: MovieService by lazy {
        retrofit.create(MovieService::class.java)
    }



}