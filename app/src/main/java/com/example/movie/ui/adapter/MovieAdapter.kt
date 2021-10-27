package com.example.movie.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.domain.Movie
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(
    val onClickMovie: (movie: Movie) -> Unit,
    val favorite: (movie: Movie, status: Int) -> Unit
) : RecyclerView.Adapter<MovieAdapter.Viewholder>() {

    private var movies: List<Movie> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.movie_item, parent,
            false
        )
        return Viewholder(view, onClickMovie, favorite)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        movies[position].let { holder.bindView(it) }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun setMovie(list: List<Movie>) {
        movies = list
        notifyDataSetChanged()
    }

    class Viewholder(
        itemView: View,
        val onClickMovie: (movie: Movie) -> Any,
        val favorite: (movie: Movie, status: Int) -> Any
    ) : RecyclerView.ViewHolder(itemView) {

        fun bindView(movie: Movie) {

            itemView.title_movie.text = movie.title
            itemView.released.text = movie.released
            itemView.genre.text = movie.genre

            if (movie.favorite == 1) {
                itemView.favorite.setImageResource(R.drawable.ic_best)
            } else {
                itemView.favorite.setImageResource(R.drawable.ic_best_no)
            }
            Glide.with(itemView.context).load(movie.poster).into(itemView.movie_image)
            onClickListener(movie)
        }

        private fun onClickListener(movie: Movie) {

            itemView.movie_image.setOnClickListener {
                onClickMovie(movie)
            }

            itemView.favorite.setOnClickListener {
                if (movie.favorite == 1) {
                    itemView.favorite.setImageResource(R.drawable.ic_best_no)
                    favorite(movie, 0)
                } else {
                    itemView.favorite.setImageResource(R.drawable.ic_best)
                    favorite(movie, 1)
                }
            }
        }
    }
}