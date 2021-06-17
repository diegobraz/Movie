package com.example.movie.view.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.Repository.remote.MovieRepositore
import com.example.movie.domain.Movie
import kotlinx.coroutines.launch

class MainViewModel(private val repositore: MovieRepositore): ViewModel() {

    var movies = MutableLiveData<List<Movie>>()

    var click = MutableLiveData<Boolean>()


    fun getMovie(context: Context){
         viewModelScope.launch {
             movies.value = repositore.getPost(context)
         }
     }

    fun seachMovie(movie:String, context: Context){
        viewModelScope.launch {
            movies.value = repositore.seachMovie(movie, context)
        }
    }
   fun rating(movie:Movie,rate : Float,context: Context){
       viewModelScope.launch {
           movie.rating = rate
           repositore.SavePreference(context,movie)
       }
   }
    fun selectTopMovie(context: Context){
        viewModelScope.launch {
            movies.value = repositore.topMovies(context)
        }
    }
    fun setClick(value :Boolean){
        click.value = value
    }
}