package com.example.funnumberfacts.di

import android.content.Context
import com.example.funnumberfacts.BuildConfig
import com.example.funnumberfacts.data.ApiUrl
import com.example.funnumberfacts.db.AppDatabase
import com.example.funnumberfacts.db.NumberFactDao
import com.example.funnumberfacts.db.createAppDatabase
import com.example.funnumberfacts.network.service.ApiServiceFactory
import com.example.funnumberfacts.network.service.ApiServiceFactoryImpl
import com.example.funnumberfacts.network.service.NumberFactService
import com.example.funnumberfacts.repository.NumberFactRepository
import com.example.funnumberfacts.repository.NumberFactRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun apiUrl(): ApiUrl = ApiUrl(BuildConfig.REST_URL)

    @Singleton
    @Provides
    fun apiServiceFactory(apiUrl: ApiUrl): ApiServiceFactory = ApiServiceFactoryImpl(apiUrl)

    @Singleton
    @Provides
    fun provideNumberService(factory: ApiServiceFactory): NumberFactService =
        factory.numberService()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        createAppDatabase(context)

    @Singleton
    @Provides
    fun provideNumberFactDao(appDatabase: AppDatabase): NumberFactDao =
        appDatabase.getNumberFactDao()

    @Singleton
    @Provides
    fun provideNumberFactRepository(dao: NumberFactDao): NumberFactRepository =
        NumberFactRepositoryImpl(dao)
}