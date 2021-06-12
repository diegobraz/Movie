package com.example.movie.api.Repository

import com.example.movie.api.Repository.remote.MovieInstance
import com.example.movie.domain.Movie

class MovieRepositore {

    suspend fun getPost(): List<Movie>{
        return MovieInstance.api.getPost()
    }

}