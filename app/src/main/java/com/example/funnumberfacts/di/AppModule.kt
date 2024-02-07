package com.example.funnumberfacts.di

import com.example.funnumberfacts.BuildConfig
import com.example.funnumberfacts.data.ApiUrl
import com.example.funnumberfacts.network.ApiServiceFactory
import com.example.funnumberfacts.network.ApiServiceFactoryImpl
import com.example.funnumberfacts.network.numberservice.NumberRetrofitService
import com.example.funnumberfacts.network.numberservice.NumberService
import com.example.funnumberfacts.network.numberservice.NumberServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideNumberService(factory: ApiServiceFactory): NumberService = factory.numberService()
}