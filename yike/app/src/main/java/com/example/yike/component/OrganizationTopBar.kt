package com.example.yike.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OriganizationTopBar(
    text:String = "",
    clickEvent:()->Unit = {},
) {
    TopAppBar(
        backgroundColor = Color(0xFFFFFFFF),
    ) {
        Row(){
            Column() {
                IconButton(onClick = clickEvent) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(24.dp),
                        tint = Color(0xFF000000)
                    )
                }
            }
            Column() {
                Text(
                    text = text,
                    color = Color(0xFFFFFFFF),
                    modifier = Modifier
                        .padding(start = 20.dp),
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}