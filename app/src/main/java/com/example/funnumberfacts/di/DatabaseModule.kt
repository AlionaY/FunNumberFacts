package com.example.funnumberfacts.di

import android.content.Context
import com.example.funnumberfacts.db.AppDatabase
import com.example.funnumberfacts.db.NumberFactDao
import com.example.funnumberfacts.db.createAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        createAppDatabase(context)

    @Singleton
    @Provides
    fun provideNumberFactDao(appDatabase: AppDatabase): NumberFactDao =
        appDatabase.getNumberFactDao()
}