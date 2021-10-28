package com.example.movie.repository

import com.example.movie.api.MovieService
import com.example.movie.data.dao.MovieDao
import com.example.movie.data.boundary.MovieDataSource
import com.example.movie.domain.Movie
import javax.inject.Inject

class MovieRepositoryImpl  @Inject constructor(
    private val movieDao: MovieDao,
    private val movieService: MovieService
    ) : MovieDataSource {

    override suspend fun getPost(): List<Movie> {
        val movieApi = movieService.getPost()
        movieDao.save(movieApi)
        return movieDao.selectAll()
    }

    override suspend fun searchMovie(movie: String): List<Movie> {
        return movieDao.get(movie)
    }

    override suspend fun savePreference(movie: Movie) {
        movieDao.savePreference(movie)
    }

    override suspend fun topMovies(): List<Movie> {
        return movieDao.selectFavorites()
    }

    override suspend fun localMovie(): List<Movie> {
        return movieDao.selectAll()
    }
}