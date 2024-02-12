package com.example.funnumberfacts.db

import android.content.Context
import androidx.room.Room

private const val DATABASE_NAME = "appdb"

fun createAppDatabase(context: Context) = Room
    .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
    .fallbackToDestructiveMigration()
    .build()