package com.example.yike.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import com.example.yike.R
import com.example.yike.activity.ActivityDetailActivity
import com.example.yike.data.ActivityDetail
import com.example.yike.data.activityDetailList


@Preview
@Composable
fun ActivityScreen(){
    LazyColumn(Modifier){
        item {
            ActivityTable()
        }
        items(activityDetailList){
            ActivityItemS(it)
        }
    }
}

@Composable
fun ActivityTable(){
    Text(
        text = "活动一览",
        color = Color.Black,
        style = MaterialTheme.typography.h5,
        modifier = Modifier.padding(16.dp,16.dp,16.dp,16.dp)
    )
}


@Composable
fun ActivityItemS(item: ActivityDetail){
    Column() {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .size(600.dp, 170.dp)
                .clickable { },
            painter = painterResource(item.img),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Box(
            modifier = Modifier
                .size(550.dp, 85.dp)
                .padding(3.dp, 0.dp)
                .clickable { }
                .background(Color.White)
        ){
            Row(modifier = Modifier.padding(all = 8.dp)) {
                Image(
                    painter = painterResource(item.organizer.img),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(0.dp, 5.dp)
                        .size(55.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxSize()
//                .border(1.5.dp, MaterialTheme.colors.secondary, RoundedCornerShape(7.dp))
                )
                Spacer(modifier = Modifier.width(15.dp))

                Column (modifier = Modifier.size(300.dp,65.dp)){
                    Text(
                        text = item.title,
                        color = Color.Black,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = item.organizer.name,
                        color = Color(0xFF7A7A7A),
                        style = MaterialTheme.typography.caption
                    )
                }
                Spacer(modifier = Modifier.width(15.dp))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.Add,null)
                }
            }
        }
    }

}




//
//@Preview
//@Composable
//fun test(){
//    Text("TJ")
//}