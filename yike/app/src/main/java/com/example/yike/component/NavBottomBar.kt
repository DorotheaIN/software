package com.example.yike.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController

@Composable
fun NavBottomBar(
    navController: NavController
) {
    BottomAppBar(
        backgroundColor = MaterialTheme.colors.primary,
    ) {
        BottomButton(
            selected = true,
            icon = Icons.Default.Home,
            labelText = "Home",
            onClick = {
                navController.navigate("discuss")
            }
        )
        BottomButton(
            selected = false,
            icon = Icons.Default.FavoriteBorder,
            labelText = "Activity",
            onClick = {
                navController.navigate("activity")
            }
        )
        BottomButton(
            selected = false,
            icon = Icons.Default.AccountCircle,
            labelText = "Profile",
            onClick = {
                navController.navigate("organization")
            },
        )
    }
}

@Composable
private fun RowScope.BottomButton(
    selected: Boolean,
    icon: ImageVector,
    labelText: String,
    onClick: () -> Unit = {}
) {
    BottomNavigationItem(
        selected = selected,
        onClick = onClick,
        icon = {
            Icon(
                icon,
                contentDescription = null,
            )
        },
        label = {
            Text(labelText)
        }
    )
}