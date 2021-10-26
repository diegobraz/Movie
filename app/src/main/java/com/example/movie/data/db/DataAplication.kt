package com.example.movie.data.db

import android.app.Application
import android.content.Context
import com.example.movie.Repository.MovieRepositore

class DataAplication: Application() {
    private val database by lazy { AppDataBase.getDatabase(this) }
    val repository by lazy { MovieRepositore(database.UserDao()) }
}
