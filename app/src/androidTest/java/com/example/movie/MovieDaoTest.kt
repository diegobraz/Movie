package com.example.movie

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.movie.data.dao.MovieDao
import com.example.movie.data.db.AppDataBase
import com.example.movie.domain.Movie
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MovieDaoTest {
    private lateinit var userDao: MovieDao
    private lateinit var db: AppDataBase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDataBase::class.java).build()
        userDao = db.UserDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }


}