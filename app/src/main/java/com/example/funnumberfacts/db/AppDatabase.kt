package com.example.funnumberfacts.db

import androidx.room.Database
import androidx.room.RoomDatabase

private const val VERSION_DB = 2

@Database(entities = [FactItem::class], version = VERSION_DB)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getNumberFactDao(): NumberFactDao
}