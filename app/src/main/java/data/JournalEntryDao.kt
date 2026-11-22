package com.example.projectjournal.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/*
All the methods that the entries use.
You can insert, update and delete them.
 */
@Dao
interface JournalEntryDao {

    // Flow means the list updates automatically when data changes.
    @Query("SELECT * FROM entries ORDER BY date DESC")
    fun getAll(): Flow<List<JournalEntry>>

    @Insert
    suspend fun insert(entry: JournalEntry)

    @Update
    suspend fun update(entry: JournalEntry)

    @Delete
    suspend fun delete(entry: JournalEntry)
}