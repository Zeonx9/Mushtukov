package com.example.tinkofffintechlab.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.example.tinkofffintechlab.domain.model.Film
import com.example.tinkofffintechlab.domain.model.FilmDetails
import com.example.tinkofffintechlab.presentation.home_page.HomeScreenEvent

@Composable
fun AdaptiveFilmList(
    films: List<Film>,
    filmDetails: FilmDetails?,
    paddingValues: PaddingValues = PaddingValues(),
    onLongPress: (Film) -> Unit,
    onClickPortrait: (Film) -> Unit,
    onClickLandscape: (Film) -> Unit
) {
    val configuration = LocalConfiguration.current
    if (configuration.screenWidthDp > 800) {
        Row {
            Column(Modifier.weight(1f)) {
                FilmList(
                    paddingValues = paddingValues,
                    films = films,
                    onLongPress = onLongPress,
                    onClick = onClickLandscape
                )
            }
            Column(Modifier.weight(1f)) {
                FilmDetailsComponent(
                    filmDetails = filmDetails,
                    paddingValues = paddingValues
                )
            }
        }
    } else {
        FilmList(
            paddingValues = paddingValues,
            films = films,
            onLongPress = onLongPress,
            onClick = onClickPortrait
        )
    }
}