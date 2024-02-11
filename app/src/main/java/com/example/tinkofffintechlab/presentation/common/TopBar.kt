package com.example.tinkofffintechlab.presentation.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.example.tinkofffintechlab.presentation.ui.theme.Blue50

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String, onSearchButtonClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            IconButton(onClick = onSearchButtonClick) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "иконка поиск",
                    tint = Blue50
                )
            }
        }
    )
}