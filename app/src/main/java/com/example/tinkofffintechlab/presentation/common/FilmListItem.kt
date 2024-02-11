package com.example.tinkofffintechlab.presentation.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.tinkofffintechlab.domain.model.Film
import com.example.tinkofffintechlab.presentation.ui.theme.Blue50

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FilmListItem(item: Film, onLongPress: (Film) -> Unit = {}, onClick: (Film) -> Unit) {
    Card (
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onLongClick = { onLongPress(item) }
            ) { onClick(item) }
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SubcomposeAsyncImage(
                model = item.posterUrl,
                contentDescription = "poster",
                modifier = Modifier
                    .size(80.dp, 120.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(4.dp)))
            ) {
                val state = painter.state
                if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                    CircularProgressIndicator()
                } else {
                    SubcomposeAsyncImageContent()
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column() {
                Text(
                    text = item.name,
                    fontStyle = MaterialTheme.typography.displayMedium.fontStyle,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "${item.genre} (${item.year})")
            }
            Spacer(modifier = Modifier.weight(1f))
            if (item.isFavorite) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "favoriteIcon",
                    tint = Blue50
                )
            }

        }
    }
}

@Preview
@Composable
fun PreviewFilmListItem() {
    FilmListItem(
        item = Film(
            1,
            "мастер",
            "2024",
            "url",
            "драмма"
        )
    ) {}
}