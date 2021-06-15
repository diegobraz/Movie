package com.example.movie.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey  val title :String,
    @ColumnInfo(name = "_movie_descricao")
    val year: String,
    val rated: String,
    val released : String,
    val runtime : String,
    val genre : String,
    val director : String,
    val writer : String,
    val actors : String,
    val plot : String,
    val poster: String
): Serializable
