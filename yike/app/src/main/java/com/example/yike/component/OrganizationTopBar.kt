package com.example.yike.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun OriganizationTopBar() {
    TopAppBar(
        backgroundColor = Color(0xffdaa545),
    ) {
        IconButton(onClick = {
//            navController.navigate("discuss")
        }) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .size(24.dp),
                tint = Color.White
            )
        }
    }
}