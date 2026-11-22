package com.example.projectjournal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectjournal.data.AppDatabase
import com.example.projectjournal.data.JournalEntry
import com.example.projectjournal.data.Mood
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/*
The ViewModel sits between the database and the screens */
class JournalViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getDatabase(application).journalEntryDao()

    // All entries, newest first. The list screen will watch this.
    val entries: StateFlow<List<JournalEntry>> = dao.getAll()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Called by the create screen when the user saves
    fun addEntry(title: String, content: String, mood: Mood) {
        viewModelScope.launch {
            dao.insert(JournalEntry(title = title, content = content, mood = mood))
        }
    }

    fun deleteEntry(entry: JournalEntry) {
        viewModelScope.launch { dao.delete(entry) }
    }
}