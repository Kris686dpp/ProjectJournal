package com.example.projectjournal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                // true = show the create screen, false = show the list
                var showCreate by rememberSaveable { mutableStateOf(false) }

                if (showCreate) {
                    // Phone's back button returns to the list instead of closing the app
                    BackHandler { showCreate = false }
                    CreateScreen(onDone = { showCreate = false })
                } else {
                    EntryListScreen(onAddClick = { showCreate = true })
                }
            }
        }
    }
}