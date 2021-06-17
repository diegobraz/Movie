package com.example.movie.Repository

import android.content.Context
import android.util.Log
import com.example.movie.api.MovieInstance
import com.example.movie.data.db.AppDataBase
import com.example.movie.domain.Movie

class MovieRepositore(

) {
    suspend fun getPost(context:Context): List<Movie>{
        val movieDao = AppDataBase.getDatabase(context = context).UserDao()
        val movieApi = MovieInstance.api.getPost()
        movieDao.save(movieApi)
        val localResposotire = movieDao.selectAll()
        return localResposotire


    }

    suspend fun seachMovie(movie:String,context: Context):List<Movie>{
        val movieDao = AppDataBase.getDatabase(context = context).UserDao()
        Log.d("diegoLocal","${movieDao.get(movie)}")
       return movieDao.get(movie)

    }

    suspend fun SavePreference(context: Context,movie : Movie){
        val movieDao = AppDataBase.getDatabase(context = context).UserDao()
         movieDao.savePreference(movie)
    }

    suspend fun topMovies(context: Context):List<Movie>{
        val movieDao = AppDataBase.getDatabase(context = context).UserDao()
        return movieDao.selctTopMovie()

    }

    suspend fun localgetMovie(context: Context): List<Movie>{
        val movieDao = AppDataBase.getDatabase(context = context).UserDao()
        return movieDao.selectAll()
    }


}