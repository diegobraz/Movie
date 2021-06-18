package com.example.movie.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movie.data.dao.MovieDao
import com.example.movie.domain.Movie

@Database(entities = [Movie::class], version = 4)
abstract class AppDataBase: RoomDatabase() {
    abstract fun UserDao(): MovieDao

    companion object {

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "database-name"
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }
}