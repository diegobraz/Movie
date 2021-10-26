package com.example.movie.Repository


import com.example.movie.api.MovieInstance
import com.example.movie.data.dao.MovieDao
import com.example.movie.domain.Movie

class MovieRepositore(private val movieDao: MovieDao) {

    suspend fun getPost(): List<Movie> {
        val movieApi = MovieInstance.api.getPost()
        movieDao.save(movieApi)
        return movieDao.selectAll()
    }

    suspend fun seachMovie(movie: String): List<Movie> {
        return movieDao.get(movie)
    }

    suspend fun SavePreference(movie: Movie) {
        movieDao.savePreference(movie)
    }

    suspend fun topMovies(): List<Movie> {
        return movieDao.selctfavorite()

    }

    suspend fun localgetMovie(): List<Movie> {
        return movieDao.selectAll()
    }
}