package com.example.movie.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.domain.Movie
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetail : AppCompatActivity() {

    lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        movie = intent.getSerializableExtra("movie") as Movie
        loadMovie()
    }

    private fun loadMovie() {
        Glide.with(this).load(movie.poster).into(movie_Image_detail)
        title_movie_deatil.text = movie.title
        description_movie_deatil.text = movie.plot
    }
}