package com.example.movie.api.Repository.remote

import com.example.movie.domain.Movie
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET


interface MovieService {

    @GET("saga")
  suspend  fun getPost(): List<Movie>

}