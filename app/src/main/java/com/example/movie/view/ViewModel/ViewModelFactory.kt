package com.example.movie.view.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movie.Repository.remote.MovieRepositore
import com.example.movie.data.db.AppDataBase

@Suppress("UNCHECKED_CAST")
class ViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repositore = MovieRepositore()) as T
    }


}