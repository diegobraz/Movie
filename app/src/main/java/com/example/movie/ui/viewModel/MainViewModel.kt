package com.example.movie.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.boundary.MovieDataSource
import com.example.movie.domain.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MovieDataSource) : ViewModel() {

    var movies = MutableLiveData<List<Movie>>()

    fun getMovie() {
        viewModelScope.launch {
            movies.value = repository.getPost()
        }
    }

    fun getMovieLocal() {
        viewModelScope.launch {
            movies.value = repository.localMovie()
        }
    }

    fun searchMovie(movie: String) {
        viewModelScope.launch {
            movies.value = repository.searchMovie(movie)
        }
    }

    fun favorite(movie: Movie, status: Int) {
        viewModelScope.launch {
            movie.favorite = status
            repository.savePreference(movie)
        }
    }

    fun selectTopMovie() {
        viewModelScope.launch {
            movies.value = repository.topMovies()
        }
    }
}