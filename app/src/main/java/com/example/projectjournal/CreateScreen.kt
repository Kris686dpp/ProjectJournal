package com.example.projectjournal

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectjournal.data.Mood
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton

/*
This is the screen where new journal entries as created.
*/
@Composable
fun CreateScreen(
    viewModel: JournalViewModel = viewModel(),
    onDone: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var mood by remember { mutableStateOf(Mood.NEUTRAL) }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            //The header
            Text("New entry", fontSize = 24.sp)

            // TextBox for the Title
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            //Chips for the Mood
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)) {
                Mood.entries.forEach { option ->
                    FilterChip(
                        selected = mood == option,
                        onClick = { mood = option },
                        label = { Text(option.name) },
                    )
                }
            }

            //Textbox for the content
            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Entry") },
                modifier = Modifier.fillMaxWidth().height(200.dp)
            )

            Button(
                onClick = {
                    viewModel.addEntry(title, content, mood)
                    onDone() // tells the app to go back to the list
                },
                enabled = title.isNotBlank(), // can't save without a title
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save")
            }
        }
    }
}