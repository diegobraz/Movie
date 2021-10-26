package com.example.movie.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.Repository.MovieRepositore
import com.example.movie.domain.Movie
import kotlinx.coroutines.launch

class MainViewModel(private val repositore: MovieRepositore) : ViewModel() {

    var movies = MutableLiveData<List<Movie>>()

    fun getMovie() {
        viewModelScope.launch {
            movies.value = repositore.getPost()
        }
    }

    fun getMovieLocal() {
        viewModelScope.launch {
            movies.value = repositore.localgetMovie()
        }
    }

    fun seachMovie(movie: String) {
        viewModelScope.launch {
            movies.value = repositore.seachMovie(movie)
        }
    }

    fun favorite(movie: Movie, status: Int) {
        viewModelScope.launch {
            movie.favorite = status
            repositore.SavePreference(movie)
        }
    }

    fun selectTopMovie() {
        viewModelScope.launch {
            movies.value = repositore.topMovies()
        }
    }
}