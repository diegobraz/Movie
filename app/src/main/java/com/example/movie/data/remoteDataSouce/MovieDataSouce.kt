package com.example.movie.data.remoteDataSouce

import com.example.movie.domain.Movie

interface MovieDataSouce {
    suspend fun getPost(): List<Movie>
    suspend fun seachMovie(movie: String): List<Movie>
    // todo refatorar o nome
    suspend fun SavePreference(movie: Movie)
    suspend fun topMovies(): List<Movie>
    // todo refatorar o nome
    suspend fun localgetMovie(): List<Movie>
}