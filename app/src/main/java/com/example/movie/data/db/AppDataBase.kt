package com.example.movie.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movie.data.dao.MovieDao
import com.example.movie.domain.Movie

@Database(entities = [Movie::class], version = 4)
abstract class AppDataBase : RoomDatabase() {
    abstract fun UserDao(): MovieDao
}