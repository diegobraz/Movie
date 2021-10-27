package com.example.movie.di

import com.example.movie.Repository.MovieRepositore
import com.example.movie.data.remoteDataSouce.MovieDataSouce
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Singleton
    @Binds
    //todo mudar o nome
    abstract fun provideMovieDataSource(dataSouce: MovieRepositore):MovieDataSouce

}