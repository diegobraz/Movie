package com.example.movie.view.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.api.Repository.MovieRepositore
import com.example.movie.domain.Movie
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repositore: MovieRepositore): ViewModel() {

    var movies = MutableLiveData<List<Movie>>()


    fun getMovie(){
         viewModelScope.launch {
             movies.value = repositore.getPost()
         }
     }
}