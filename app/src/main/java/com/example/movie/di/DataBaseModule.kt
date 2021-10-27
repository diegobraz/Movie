package com.example.movie.di


import android.content.Context
import androidx.room.Room
import com.example.movie.data.db.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    @Singleton
    fun provideDataBase(
        @ApplicationContext appContext: Context
    ) = Room.databaseBuilder(appContext, AppDataBase::class.java, "MOVIE_DB")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideUserDao(db: AppDataBase) = db.UserDao()
}