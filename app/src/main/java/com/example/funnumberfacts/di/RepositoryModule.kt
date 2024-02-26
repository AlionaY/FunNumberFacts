package com.example.funnumberfacts.di

import com.example.funnumberfacts.repository.NumberFactRepository
import com.example.funnumberfacts.repository.NumberFactRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindRepository(impl: NumberFactRepositoryImpl): NumberFactRepository
}