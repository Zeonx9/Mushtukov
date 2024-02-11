package com.example.tinkofffintechlab.presentation.common

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.tinkofffintechlab.R
import com.example.tinkofffintechlab.domain.model.FilmDetails

@Composable
fun FilmDetailsComponent(
    filmDetails: FilmDetails?,
    paddingValues: PaddingValues = PaddingValues()
) {
    if (filmDetails == null) {
        EmptyListPlaceholder(textResId = R.string.no_film_details_message)
        return
    }
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        SubcomposeAsyncImage(
            model = filmDetails.posterUrl,
            contentDescription = stringResource(R.string.poster_content_description),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        ) {
            val painterState = painter.state
            if (painterState is AsyncImagePainter.State.Loading || painterState is AsyncImagePainter.State.Error) {
                CircularProgressIndicator()
            } else {
                SubcomposeAsyncImageContent()
            }
        }
        Column (
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = filmDetails.name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(text = filmDetails.description)
            Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp)
            Text(text = "Жанры: " + filmDetails.genres.joinToString { it.genre })
            Text(text = "Страны: " + filmDetails.countries.joinToString { it.country })
        }
    }
}