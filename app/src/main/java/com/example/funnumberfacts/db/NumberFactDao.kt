package com.example.funnumberfacts.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NumberFactDao {
    @Query("SELECT * FROM $NUMBER_FACTS_TABLE")
    fun getFactHistory(): List<FactItem>

    @Query("SELECT * FROM $NUMBER_FACTS_TABLE WHERE id == :itemId")
    fun getFactById(itemId: Int): FactItem

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToHistory(item: FactItem)

    @Delete
    fun deleteHistory()
}