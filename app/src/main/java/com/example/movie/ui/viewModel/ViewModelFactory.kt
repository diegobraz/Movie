package com.example.movie.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movie.Repository.MovieRepositore

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: MovieRepositore): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repositore = repository) as T
    }
}