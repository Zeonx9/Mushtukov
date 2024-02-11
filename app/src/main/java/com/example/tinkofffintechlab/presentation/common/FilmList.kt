package com.example.tinkofffintechlab.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tinkofffintechlab.domain.model.Film

@Composable
fun FilmList(
    paddingValues: PaddingValues,
    films: List<Film>,
    onLongPress: (Film) -> Unit = {},
    onClick: (Film) -> Unit
) {
    LazyColumn (
        contentPadding = paddingValues,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        items(films) { item ->
            FilmListItem(
                item = item,
                onLongPress = onLongPress,
                onClick = onClick
            )
        }
    }
}