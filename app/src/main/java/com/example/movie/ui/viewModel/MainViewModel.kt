package com.example.movie.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.Repository.MovieRepositore
import com.example.movie.data.remoteDataSouce.MovieDataSouce
import com.example.movie.domain.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repositore: MovieDataSouce) : ViewModel() {

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