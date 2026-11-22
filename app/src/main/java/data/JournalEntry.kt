package com.example.projectjournal.data


import androidx.room.Entity
import androidx.room.PrimaryKey

enum class Mood { GOOD, NEUTRAL, BAD }

/* A JOURNAL ENTRY: What the whole program is built around.
It contains a Title, Content, Mood and Date/Time.
Entries are going to be viewed in a list on the homepage.
 */
@Entity(tableName = "entries")
data class JournalEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String,
    val mood: Mood,
    val date: Long = System.currentTimeMillis()
)