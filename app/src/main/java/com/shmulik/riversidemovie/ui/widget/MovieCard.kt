package com.shmulik.riversidemovie.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.shmulik.domain.entities.PreViewMovieEntity

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    item: PreViewMovieEntity,
    isSelected: Boolean,
) {

    val highlightColor = if (isSelected) Color.Red else Color.Transparent

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Card(
            shape = CardDefaults.elevatedShape,
            modifier = modifier
                .height(200.dp)
                .border(3.dp, highlightColor),
        ) {
            Box(modifier = Modifier.background(color = Color.Gray)) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    model = item.urlPic,
                    contentDescription = null
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)
                        .background(Color.Black.copy(alpha = 0.5f))
                        .padding(8.dp),
                    text = item.title,
                    color = Color.White
                )
            }

        }

        Text(text = "release year: ${item.year}", color = Color.Black)
    }

}

