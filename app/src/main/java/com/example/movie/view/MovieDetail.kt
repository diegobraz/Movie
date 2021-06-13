package com.example.movie.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.domain.Movie
import com.example.movie.view.ViewModel.MainViewModel
import com.example.movie.view.ViewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.movie_item.*
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieDetail : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this, ViewModelFactory())
            .get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        viewModel.getMovie()
        viewModel.movies.observe(this, {

//            loadMovie()

        })


    }

//    private fun loadMovie() {
////        Glide.with(this).load(movie.poster).into(movie_Image_detail)
//        title_movie_deatil.text = movie.title
//    }

}