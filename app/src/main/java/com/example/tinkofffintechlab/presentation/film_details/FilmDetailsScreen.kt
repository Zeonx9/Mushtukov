package com.example.tinkofffintechlab.presentation.film_details

import android.hardware.biometrics.BiometricManager.Strings
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.tinkofffintechlab.R
import com.example.tinkofffintechlab.domain.model.Country
import com.example.tinkofffintechlab.domain.model.FilmDetails
import com.example.tinkofffintechlab.domain.model.Genre
import com.example.tinkofffintechlab.presentation.common.FilmDetailsComponent
import com.example.tinkofffintechlab.presentation.common.LoadingBar
import com.example.tinkofffintechlab.presentation.common.NetworkError
import com.example.tinkofffintechlab.presentation.common.OnlyUpButtonTopBar
import com.example.tinkofffintechlab.presentation.ui.theme.Blue50

@Composable
fun FilmDetailsScreen(
    navController: NavController,
    state: FilmDetailsState,
    onEvent: (FilmDetailsEvent) -> Unit
) {
    if (state.isLoading) {
        LoadingBar()
    } else if (state.error.isNotBlank()) {
        NetworkError(message = stringResource(R.string.network_error_message)) {
            onEvent(FilmDetailsEvent.ReloadEvent)
        }
    } else {
        Scaffold (
            topBar = {
                OnlyUpButtonTopBar {
                    onEvent(FilmDetailsEvent.UpEvent(navController))
                }
            }
        ) { paddingValues ->
            FilmDetailsComponent(
                filmDetails = state.filmDetails,
                PaddingValues(bottom = paddingValues.calculateBottomPadding())
            )
        }
    }
}

@Preview
@Composable
fun PreviewFilmDetailsScreen() {
    FilmDetailsScreen(
        navController = rememberNavController(),
        state = FilmDetailsState(
            filmDetails = FilmDetails(
                id = 1,
                name = "мастер",
                posterUrl = "https://kinopoiskapiunofficial.tech/images/posters/kp/1115471.jpg",
                description = "Москва, 1930-е годы. Известный писатель на взлёте своей карьеры внезапно оказывается в центре литературного скандала. Спектакль по его пьесе снимают с репертуара, коллеги демонстративно избегают встречи, в считанные",
                genres = listOf(Genre("по роману")),
                countries = listOf(Country("Россия"))
            )
        ),
        onEvent = {}
    )
}