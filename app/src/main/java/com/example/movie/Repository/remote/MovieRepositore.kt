package com.example.movie.Repository.remote

import android.content.Context
import android.content.ContextWrapper
import com.example.movie.api.MovieInstance
import com.example.movie.data.dao.MovieDao
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

}