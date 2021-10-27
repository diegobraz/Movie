package com.example.movie.Repository

import com.example.movie.api.MovieService
import com.example.movie.data.dao.MovieDao
import com.example.movie.data.remoteDataSouce.MovieDataSouce
import com.example.movie.domain.Movie
import javax.inject.Inject

class MovieRepositore  @Inject constructor(
    private val movieDao: MovieDao,
    private val movieService: MovieService
    ) :MovieDataSouce {

    override suspend fun getPost(): List<Movie> {
        val movieApi = movieService.getPost()
        movieDao.save(movieApi)
        return movieDao.selectAll()
    }

    override suspend fun seachMovie(movie: String): List<Movie> {
        return movieDao.get(movie)
    }
    // todo refatorar o nome
    override suspend fun SavePreference(movie: Movie) {
        movieDao.savePreference(movie)
    }

    override suspend fun topMovies(): List<Movie> {
        return movieDao.selctfavorite()

    }
    // todo refatorar o nome
    override suspend fun localgetMovie(): List<Movie> {
        return movieDao.selectAll()
    }
}