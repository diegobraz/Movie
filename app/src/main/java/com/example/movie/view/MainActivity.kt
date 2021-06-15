package com.example.movie.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.data.db.AppDataBase
import com.example.movie.domain.Movie
import com.example.movie.view.ViewModel.MainViewModel
import com.example.movie.view.ViewModel.ViewModelFactory
import com.example.movie.view.adapter.MovieAdapter
import retrofit2.Response

class MainActivity : AppCompatActivity(),SearchView.OnQueryTextListener {

private val viewModel by lazy {
    ViewModelProvider(this,ViewModelFactory())
        .get(MainViewModel::class.java)
}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadProdutos()
    }

    private fun loadProdutos() {
        viewModel.getMovie(this)
        viewModel.movies.observe(this, Observer {
            loadRecycleView(it)
        })
    }

    private fun loadRecycleView(movie : List<Movie>){
        val recyclerView = findViewById<RecyclerView>(R.id.movie_list)
        recyclerView.post {
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = MovieAdapter(movie,onClickMovie = { movie ->
                onCreateDetailMovie(movie)
            })

        }
    }

    private fun onCreateDetailMovie(movie:Movie) {
        startActivity(
            Intent(
                this,
                MovieDetail::class.java
            ).apply {
                putExtra("movie",movie)
            }
        ) }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        val item = menu.findItem(R.id.buscar_movie)
        val seachView = item?.actionView as SearchView
        seachView.isSubmitButtonEnabled = true

        seachView.setOnQueryTextListener(this)

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null && query !=" "){
            seachDataBase(query)
        }
        else{

            loadProdutos()

        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null && newText!= " "){
            seachDataBase(newText)
        }else{
          loadProdutos()
        }
        return true
    }
    private fun seachDataBase(movie:String){
        viewModel.seachMovie(movie,this)
        viewModel.movies.observe(this, Observer {
            loadRecycleView(it)
        })
    }


}