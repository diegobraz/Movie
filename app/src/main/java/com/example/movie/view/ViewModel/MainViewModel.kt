package com.example.movie.view.ViewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.Repository.remote.MovieRepositore
import com.example.movie.domain.Movie
import kotlinx.coroutines.launch

class MainViewModel(private val repositore: MovieRepositore): ViewModel() {

    var movies = MutableLiveData<List<Movie>>()


    fun getMovie(context: Context){
         viewModelScope.launch {
             movies.value = repositore.getPost(context)
         }
     }
}