package com.yeditepe.mindup

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainPage() {
    var mood by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    var savedEntries by remember { mutableStateOf<List<Pair<String, String>>>(emptyList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "How are you feeling today?",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Sonsuz döngü mood carousel
        InfiniteMoodCarousel { selectedMood ->
            mood = selectedMood
        }

        Spacer(modifier = Modifier.height(32.dp))

        if (mood.isNotEmpty()) {
            Text(
                text = "You selected: $mood",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = note,
            onValueChange = { note = it },
            label = { Text("Add a note for today") },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (mood.isNotEmpty() && note.isNotEmpty()) {
                    savedEntries = savedEntries + Pair(mood, note)
                    mood = ""
                    note = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Entry")
        }

        Spacer(modifier = Modifier.height(32.dp))

        if (savedEntries.isNotEmpty()) {
            Text(
                text = "Your Entries",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(8.dp))
            savedEntries.forEachIndexed { _, entry ->
                EntryCard(mood = entry.first, note = entry.second)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainPage(){
    MyAppTheme{
        MainPage()
    }
}
