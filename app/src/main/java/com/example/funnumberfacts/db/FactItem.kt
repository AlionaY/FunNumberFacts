package com.example.funnumberfacts.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val NUMBER_FACTS_TABLE = "numberfactsdb"
@Entity(tableName = NUMBER_FACTS_TABLE)
data class FactItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "number")
    val number: Int,
    @ColumnInfo(name = "text")
    val text: String
)