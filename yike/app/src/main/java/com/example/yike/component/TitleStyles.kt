package com.example.yike.component

import android.icu.text.CaseMap
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp

@Composable
fun TitleRec(
    text:String
){
    Row(Modifier.padding(10.dp,20.dp)){
        Box(
            Modifier.size(110.dp,41.dp)
        ){
            Canvas(modifier = Modifier.size(70.dp,36.dp), onDraw = {
                drawRoundRect(
                    color = Color(0xff6d72b1),
                    style = Stroke(width = 10f),
                )
            })
            Box(
                Modifier
                    .background(Color.White)
                    .align(Alignment.BottomEnd)
            ){
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high){
                    Text(
                        text = text,
                        color = Color.Black,
                        style = MaterialTheme.typography.h5,
                        fontFamily = FontFamily.SansSerif
                    )
                }
            }
        }
    }
}