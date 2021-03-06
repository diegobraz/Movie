package com.example.movie.ui

import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.domain.Movie
import com.example.movie.ui.viewModel.MainViewModel
import com.example.movie.ui.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),
    SearchView.OnQueryTextListener {

    private val viewModel by lazy {
        ViewModelProvider(this)
            .get(MainViewModel::class.java)
    }

    var clickStar = false
    var context = this
    var connectivity: ConnectivityManager? = null
    var info: NetworkInfo? = null

    private val adapter by lazy {
        MovieAdapter(
            onClickMovie = { movie ->
                onCreateDetailMovie(movie)
            },
            favorite = { movie, status ->
                viewModel.favorite(movie, status)
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.movies.observe(this, {
            adapter.setMovie(it)
        })
        configRecycleView()
        connection()
    }

    private fun connection() {
        connectivity = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        if (connectivity != null) {
            info = connectivity!!.activeNetworkInfo

            if (info != null) {
                if (info!!.state == NetworkInfo.State.CONNECTED) {
                    viewModel.getMovie()
                }
            } else {
                viewModel.getMovieLocal()
            }
        }
    }

    private fun configRecycleView() {
        val recyclerView = findViewById<RecyclerView>(R.id.movie_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun onCreateDetailMovie(movie: Movie) {
        startActivity(
            Intent(
                this,
                MovieDetail::class.java
            ).apply {
                putExtra("movie", movie)
            }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        val item = menu.findItem(R.id.buscar_movie)
        val searchView = item?.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.top_movie) {
            if (!clickStar) {
                item.setIcon(R.drawable.ic_baseline_star_full)
                clickStar = true
                viewModel.selectTopMovie()
            } else {
                item.setIcon(R.drawable.ic_baseline_star_empyte)
                clickStar = false
                connection()
            }
        }
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null && query != " ") {
            seachDataBase(query)
        } else {
            viewModel.getMovieLocal()
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null && newText != "") {
            seachDataBase(newText)
        } else {
            connection()
        }
        return true
    }

    private fun seachDataBase(movie: String) {
        viewModel.searchMovie(movie)
    }

    override fun onResume() {
        super.onResume()
        connection()
    }
}