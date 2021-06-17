package com.example.movie.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.domain.Movie
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(
  val onClickMovie: (movie:Movie) -> Any,
  val rating:(movie:Movie,rating:Float) -> Any
): RecyclerView.Adapter<MovieAdapter.Viewholder>(){

    private var movies:List<Movie> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        movies[position].let { holder.bindView(it) }
        holder.itemView.movie_image.setOnClickListener {
            onClickMovie(movies[position])
        }
        holder.itemView.rating_bar.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { p0, p1, p2 ->
                rating(movies[position],p1)
            }

    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun setMovie(list: List<Movie>){
        movies = list
        notifyDataSetChanged()
    }

    class Viewholder(itemView : View ):RecyclerView.ViewHolder(itemView) {

        fun bindView(movie: Movie){
            itemView.rating_bar.rating= movie.rating
            itemView.title_movie.text = movie.title
            itemView.released.text = movie.released
            itemView.genre.text = movie.genre
            Glide.with(itemView.context).load(movie.poster).into(itemView.movie_image)
        }

    }
}