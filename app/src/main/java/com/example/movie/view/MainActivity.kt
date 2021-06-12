package com.example.movie.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.domain.Movie
import com.example.movie.view.ViewModel.MainViewModel
import com.example.movie.view.ViewModel.ViewModelFactory
import com.example.movie.view.adapter.MovieAdapter
import retrofit2.Response

class MainActivity : AppCompatActivity() {

private val viewModel by lazy {
    ViewModelProvider(this,ViewModelFactory())
        .get(MainViewModel::class.java)
}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getMovie()
        viewModel.movies.observe(this, Observer {
            loadRecycleView(it)
        })

    }

    private fun loadRecycleView(movie : List<Movie>){
        val recyclerView = findViewById<RecyclerView>(R.id.movie_list)
        recyclerView.post {
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = MovieAdapter(movie)

        }
    }
}