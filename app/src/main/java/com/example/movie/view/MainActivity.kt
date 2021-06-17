package com.example.movie.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
    var clickStar = false
    val adapter by lazy {
        MovieAdapter(
            onClickMovie = { movie ->
                onCreateDetailMovie(movie)
            },
            rating = { movie, rating ->
                Toast.makeText(this, "deu certo aqui ${rating}", Toast.LENGTH_SHORT).show()
                viewModel.rating(movie,rating,this)
            }
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        viewModel.getMovie(this)
//        viewModel.movies.observe(this, Observer {
//            loadRecycleView(it)
//        })

        configRecycleView()
    }
    private fun configRecycleView(){
        val recyclerView = findViewById<RecyclerView>(R.id.movie_list)
        recyclerView.post {
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter
        }
    }
    private fun loadRecycleView(movie : List<Movie>){
        val recyclerView = findViewById<RecyclerView>(R.id.movie_list)
        recyclerView.post {
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = MovieAdapter(
                onClickMovie = { movie ->
                    onCreateDetailMovie(movie)
                },
                rating = { movie, rating ->
                    Toast.makeText(this, "deu certo aqui ${rating}", Toast.LENGTH_SHORT).show()
                    viewModel.rating(movie,rating,this)
                }
            )
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.top_movie){

            if (!clickStar){
                item.setIcon(R.drawable.ic_baseline_star_full)
                viewModel.setClick(true)
                clickStar = true
                viewModel.selectTopMovie(this)
//                viewModel.movies.observe(this, Observer {
//                    loadRecycleView(it)
//                })


            }else{
                item.setIcon(R.drawable.ic_baseline_star_empyte)
                  viewModel.setClick(false)
                clickStar = false
//                viewModel.getMovie(this)
//                viewModel.movies.observe(this, Observer {
//                    loadRecycleView(it)
//                })
            }

        }


        return true

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null && query !=" "){
            seachDataBase(query)
        }
        else{
            viewModel.getMovie(this)
//            viewModel.movies.observe(this, Observer {
//                loadRecycleView(it)
//            })
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null && newText!= ""){
            seachDataBase(newText)
        }else{
            viewModel.getMovie(this)
//            viewModel.movies.observe(this, Observer {
//               // loadRecycleView(it)
//                adapter.setMovie(it)
//            })
        }
        return true
    }
    private fun seachDataBase(movie:String){

        viewModel.seachMovie(movie,this)
//        viewModel.movies.observe(this, Observer {
//            // loadRecycleView(it)
//            adapter.setMovie(it)
//        })
    }


    override fun onResume() {
        super.onResume()
        viewModel.getMovie(this)
        viewModel.movies.observe(this, Observer {
//            Log.d("diegoMovie","${it.first().title}} --- ${it.first().rating}")
//            loadRecycleView(it)
            adapter.setMovie(it)
        })
    }


}