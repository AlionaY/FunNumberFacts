package com.example.funnumberfacts.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NumberFactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToHistory(item: FactItem): Long

    @Query("SELECT * FROM $NUMBER_FACTS_TABLE ORDER BY id DESC")
    fun getHistory(): PagingSource<Int, FactItem>

    @Query("SELECT * FROM $NUMBER_FACTS_TABLE WHERE id == :itemId")
    fun getFactById(itemId: Int): FactItem

    @Query("DELETE FROM $NUMBER_FACTS_TABLE")
    fun clearHistory()
}