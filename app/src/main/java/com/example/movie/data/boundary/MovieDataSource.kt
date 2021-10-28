package com.example.movie.data.boundary

import com.example.movie.domain.Movie

interface MovieDataSource {
    suspend fun getPost(): List<Movie>
    suspend fun searchMovie(movie: String): List<Movie>
    suspend fun savePreference(movie: Movie)
    suspend fun topMovies(): List<Movie>
    suspend fun localMovie(): List<Movie>
}