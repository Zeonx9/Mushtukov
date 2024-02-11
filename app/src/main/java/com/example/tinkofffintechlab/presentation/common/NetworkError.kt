package com.example.tinkofffintechlab.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tinkofffintechlab.R
import com.example.tinkofffintechlab.presentation.home_page.HomeScreenEvent
import com.example.tinkofffintechlab.presentation.ui.theme.Blue50

@Composable
fun NetworkError(message: String, color: Color = Blue50, onClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.cloud_off_outline),
            contentDescription = "Иконка нет сети",
            tint = color,
            modifier = Modifier.size(100.dp)
        )
        Text(text = message, color = color, textAlign = TextAlign.Center)
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = color
            )
        ) {
            Text(text = "Повторить")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}