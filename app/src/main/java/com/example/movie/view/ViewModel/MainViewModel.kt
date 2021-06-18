package com.example.movie.view.ViewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.Repository.MovieRepositore
import com.example.movie.domain.Movie
import kotlinx.coroutines.launch

class MainViewModel(private val repositore: MovieRepositore): ViewModel() {

    var movies = MutableLiveData<List<Movie>>()

    var click = MutableLiveData<Boolean>()
    var isConnected = MutableLiveData<Boolean>()

    fun getMovie(context: Context){
         viewModelScope.launch {
             movies.value = repositore.getPost(context)
         }
     }

    fun getMovieLocal(context: Context){
        viewModelScope.launch {
            movies.value = repositore.localgetMovie(context)
        }
    }

    fun seachMovie(movie:String, context: Context){
        viewModelScope.launch {
            movies.value = repositore.seachMovie(movie, context)
        }
    }
   fun rating(movie:Movie, rate : Float,context: Context){
       viewModelScope.launch {
           movie.rating = rate
           repositore.SavePreference(movie)
       }
   }

    fun favorite(movie:Movie, status : Int){
        viewModelScope.launch {
            movie.favorite = status
            repositore.SavePreference(movie)
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

    fun setStateConnect(status: Boolean) {
        isConnected.postValue(status)
    }
}