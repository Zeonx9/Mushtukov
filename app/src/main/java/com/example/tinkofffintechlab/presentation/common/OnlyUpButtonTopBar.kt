package com.example.tinkofffintechlab.presentation.common

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.example.tinkofffintechlab.presentation.ui.theme.Blue50

@Composable
fun OnlyUpButtonTopBar(onClick: () -> Unit) {
    Row {
        IconButton(
            onClick = onClick
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "иконка назад",
                tint = Blue50
            )
        }
    }
}