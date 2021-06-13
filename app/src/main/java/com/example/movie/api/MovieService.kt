package com.example.movie.api

import com.example.movie.domain.Movie
import retrofit2.http.GET


interface MovieService {

    @GET("saga")
  suspend  fun getPost(): List<Movie>

}