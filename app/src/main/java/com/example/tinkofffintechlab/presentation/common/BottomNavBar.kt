package com.example.tinkofffintechlab.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.tinkofffintechlab.R
import com.example.tinkofffintechlab.presentation.Screen
import com.example.tinkofffintechlab.presentation.common.BottomNavigationItems.bottomNavItems
import com.example.tinkofffintechlab.presentation.ui.theme.Blue5
import com.example.tinkofffintechlab.presentation.ui.theme.Blue50


@Composable
fun BottomNavBar(navController: NavController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(10.dp)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        bottomNavItems.forEach { item ->

            val isSelected = currentDestination?.hierarchy?.any { it.route == item.route } == true
            val containerColor = if (isSelected) item.unselectedColor else item.selectedColor
            val contentColor = if (isSelected) item.selectedColor else item.unselectedColor

            Button(
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = containerColor,
                    contentColor = contentColor
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text(text = stringResource(item.titleResId))
            }
        }
    }
}

object BottomNavigationItems {
    val bottomNavItems = listOf(
        BottomNavigationItem(
            titleResId = R.string.home_screen_title,
            route = Screen.HomeScreen.route
        ),
        BottomNavigationItem(
            titleResId = R.string.favorites_screen_title,
            route = Screen.FavoritesScreen.route
        )
    )
}

data class BottomNavigationItem(
    val titleResId: Int = -1,
    val selectedColor: Color = Blue50,
    val unselectedColor: Color = Blue5,
    val route: String = ""
)