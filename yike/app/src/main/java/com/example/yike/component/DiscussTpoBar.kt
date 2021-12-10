package com.example.yike.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DiscussTopBar(
    backEvent: () -> Unit = {}
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
    ) {
        IconButton(onClick = backEvent) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .size(24.dp)
            )
        }
//        IconButton(onClick = {
//        }) {
//            Icon(
//                Icons.Default.Search,
//                contentDescription = null,
//                modifier = Modifier
//                    .size(18.dp)
//            )
//        }
    }
}