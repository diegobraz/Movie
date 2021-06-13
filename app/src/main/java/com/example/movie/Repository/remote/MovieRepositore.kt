package com.example.movie.Repository.remote

import com.example.movie.api.MovieInstance
import com.example.movie.domain.Movie

class MovieRepositore {

    suspend fun getPost(): List<Movie>{
        return MovieInstance.api.getPost()
    }

}