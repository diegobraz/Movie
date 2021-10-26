package com.example.movie.data.dao

import androidx.room.*
import com.example.movie.domain.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun save(movie: List<Movie>)

    @Query("SELECT * FROM movie WHERE title LIKE '%' || :movieSeach || '%'")
    suspend fun get(movieSeach: String): List<Movie>

    @Query("SELECT * FROM movie")
    suspend fun selectAll(): List<Movie>

    @Query("SELECT * FROM movie ORDER BY favorite DESC")
    suspend fun selctfavorite(): List<Movie>

    @Update
    suspend fun savePreference(movie: Movie)
}