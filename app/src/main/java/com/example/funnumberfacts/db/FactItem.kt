package com.example.funnumberfacts.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.funnumberfacts.data.NumberFact

const val NUMBER_FACTS_TABLE = "numberfactsdb"

@Entity(tableName = NUMBER_FACTS_TABLE)
data class FactItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "number")
    val number: Int?,
    @ColumnInfo(name = "text")
    val text: String
)

fun FactItem.toNumberFact() = NumberFact(
    number = number,
    fact = text
)