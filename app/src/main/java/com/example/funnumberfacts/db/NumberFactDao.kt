package com.example.funnumberfacts.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NumberFactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToHistory(item: FactItem): Long

    @Query("SELECT * FROM $NUMBER_FACTS_TABLE ORDER BY id DESC LIMIT :limit OFFSET :offset")
    suspend fun getHistory(limit: Int, offset:Int): List<FactItem>

    @Query("SELECT * FROM $NUMBER_FACTS_TABLE WHERE id == :itemId")
    suspend fun getFactById(itemId: Int): FactItem

    @Query("DELETE FROM $NUMBER_FACTS_TABLE")
    suspend fun clearHistory()
}