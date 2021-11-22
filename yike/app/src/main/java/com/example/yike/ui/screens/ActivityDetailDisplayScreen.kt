package com.example.yike.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.yike.R
import com.example.yike.activity.ActivityDetailActivity
import com.example.yike.data.activityDetailList


@Composable
fun ActivityDetailDisplayScreen(id:Int){
    LazyColumn(Modifier){
        item {
            PictureDisplay(id)
        }
    }
}


@Composable
private fun Up(upPress: () -> Unit) {
}

@Composable
fun header(){

}


@Composable
fun PictureDisplay(id: Int){
    val item = activityDetailList[id]
    Text(text = item.title)
    LazyRow(Modifier){
        items(activityDetailList) {
            PictureItem(it.img)
        }
    }
}

@Composable
fun PictureItem(img:Int){
    Box(
        modifier = Modifier
            .size(400.dp, 150.dp)
            .padding(0.dp, 10.dp)
            .background(Color.White)
    ){
        Image(
            modifier = Modifier
                .fillMaxSize()
                .size(300.dp, 100.dp)
                .clickable { },
            painter = painterResource(img),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
fun InfoDisplay(){

}