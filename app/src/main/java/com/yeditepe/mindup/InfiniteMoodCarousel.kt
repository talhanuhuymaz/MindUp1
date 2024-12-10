package com.yeditepe.mindup

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfiniteMoodCarousel(onMoodSelected: (String) -> Unit) {
    val moodList = remember {
        listOf(
            R.drawable.happy to "Happy",
            R.drawable.sad to "Sad",
            R.drawable.angry to "Angry",
            R.drawable.relaxed to "Relaxed"
        )
    }
    val infiniteList = remember { List(1000) { moodList[it % moodList.size] } }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        itemsIndexed(infiniteList) { _, (imageRes, mood) ->
            CircularMoodCard(imageRes = imageRes, mood = mood, onClick = { onMoodSelected(mood) })
        }
    }
}

@Composable
fun CircularMoodCard(imageRes: Int, mood: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .clickable { onClick() },
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = mood,
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = mood, fontSize = 12.sp, color = Color.Black)
        }
    }
}

