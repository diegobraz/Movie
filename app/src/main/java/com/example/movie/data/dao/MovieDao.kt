package com.example.movie.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movie.domain.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend  fun save( movie : List<Movie>)

    @Query("SELECT * FROM movie WHERE title = :movieSeach")
    suspend fun get(movieSeach:String):Movie

    @Query("SELECT * FROM movie")
    suspend fun selectAll():List<Movie>
}