package com.example.yike.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yike.R
import com.example.yike.data.*

@Composable
fun OrganizationScreen(){
    LazyColumn(Modifier){
        item {
            header()
        }
        items(activityDetailList){
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium, // 使用 MaterialTheme 自带的形状
                elevation = 5.dp,
            ) {
                    ActivityPublishItem(it)
            }
        }
    }
}

@Preview
@Composable
fun header(){
    val organization = SSE
    Surface(
        modifier = Modifier
            .padding(bottom = 7.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium, // 使用 MaterialTheme 自带的形状
        elevation = 5.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xffdaa545), Color(0xffe08c29)),
                        start = Offset(0f, Float.POSITIVE_INFINITY),
                        end = Offset(Float.POSITIVE_INFINITY, 0f)
                    )
                )
        ) {
            Spacer(Modifier.height(40.dp))
            Box(
                modifier = Modifier
                    .size(550.dp, 85.dp)
                    .padding(15.dp, 0.dp)
                    .clickable { }
            ){
                Row(modifier = Modifier.padding(all = 8.dp)) {
                    Image(
                        painter = painterResource(organization.img),
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
                            text = organization.name,
                            color = Color.White,
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = organization.describtion,
                            color = Color(0xBAFFFFFF),
                            style = MaterialTheme.typography.caption
                        )
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Add,null)
                    }
                }
            }
            Divider(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(start = 24.dp, end = 24.dp),
                //颜色
                color = Color(0x56FFFFFF),
            )
            Spacer(Modifier.height(60.dp))
        }

    }

}


@Composable
fun PublicItem(){

}


@Composable
fun ActivityPublishItem(item:ActivityDetail){
    Row(
        Modifier
            .size(600.dp, 40.dp)
            .background(Color.White)
            .clickable { }
    ) {
        Box(
            Modifier
                .size(520.dp, 40.dp)
                .padding(35.dp, 5.dp)
        ){
            Text(
                text = item.title,
                color = Color.Black,
                style = TextStyle(
//                    fontWeight = FontWeight.Bold, //设置字体粗细
                    fontSize = 18.sp,
                    letterSpacing = 1.sp
                ),
                fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif
            )
        }
        Box(
            Modifier
                .padding(5.dp, 7.dp)
                .size(width = 30.dp, height = 40.dp)
        ){
            Icon(
                Icons.Filled.ArrowForward,
                contentDescription = "",
//                modifier = Modifier
//                    .fillMaxHeight()
            )
        }

    }
    Divider(
        Modifier
            .fillMaxWidth()
            .height(1.dp)
            .padding(start = 24.dp, end = 24.dp),
        //颜色
        color = Color(0x56979797),
    )
}