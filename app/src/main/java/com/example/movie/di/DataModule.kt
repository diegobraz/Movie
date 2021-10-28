package com.example.movie.di

import com.example.movie.repository.MovieRepositoryImpl
import com.example.movie.data.boundary.MovieDataSource
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
    abstract fun provideMovieDataSource(dataSource: MovieRepositoryImpl): MovieDataSource

}