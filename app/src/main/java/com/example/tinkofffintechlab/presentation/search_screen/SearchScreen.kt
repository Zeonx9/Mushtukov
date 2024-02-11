package com.example.tinkofffintechlab.presentation.search_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tinkofffintechlab.R
import com.example.tinkofffintechlab.presentation.common.EmptyListPlaceholder
import com.example.tinkofffintechlab.presentation.common.FilmList
import com.example.tinkofffintechlab.presentation.common.OnlyUpButtonTopBar
import com.example.tinkofffintechlab.presentation.ui.theme.Blue50

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    state: SearchScreenState,
    onEvent: (SearchEvent) -> Unit
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(end = 10.dp)
        ) {
            IconButton(onClick = { onEvent(SearchEvent.UpEvent(navController)) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.up_icon_descritption),
                    tint = Blue50
                )
            }
            SearchBar(
                query = state.query,
                onQueryChange = { onEvent(SearchEvent.QueryChangedEvent(it)) },
                onSearch = { onEvent(SearchEvent.PreformSearchEvent) },
                active = state.isActive,
                onActiveChange = { onEvent(SearchEvent.IsActiveChangedEvent(it)) },
                modifier = Modifier.weight(1f),
                content = {}
            )
        }

        if (state.results.isEmpty()) {
            EmptyListPlaceholder(textResId = R.string.empty_search_message)
        }
        else {
            FilmList(
                paddingValues = PaddingValues(),
                films = state.results,
            ) {
                onEvent(SearchEvent.FilmClickEvent(navController, it))
            }
        }
    }
}