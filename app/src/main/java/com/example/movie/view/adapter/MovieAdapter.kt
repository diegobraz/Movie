package com.example.movie.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.domain.Movie
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(
  val movies : List<Movie>
): RecyclerView.Adapter<MovieAdapter.Viewholder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        movies[position]?.let { holder.bindView(it) }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class Viewholder(itemView : View ):RecyclerView.ViewHolder(itemView) {

        fun bindView(movie : Movie){

            itemView.title_movie.text = movie.title
            itemView.released.text = movie.released
            itemView.genre.text = movie.genre
            Glide.with(itemView.context).load(movie.poster).into(itemView.movie_image)

        }

    }
}