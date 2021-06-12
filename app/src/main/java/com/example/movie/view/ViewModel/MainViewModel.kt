package com.example.movie.view.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie.domain.Movie

class MainViewModel: ViewModel() {

    var movies = MutableLiveData<List<Movie>>()


}