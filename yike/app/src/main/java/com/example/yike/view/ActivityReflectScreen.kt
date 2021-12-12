package com.example.yike.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.yike.component.NavBottomBar
import com.example.yike.component.OriganizationTopBar
import com.example.yike.viewModel.ActivityReflectViewModel
import com.example.yike.viewModel.EvaluationAnalysis
import com.example.yike.viewModel.UserInfo

@Composable
fun ActivityReflectScreen(
    navController: NavController,
    viewModel:ActivityReflectViewModel
){
    val isGet = viewModel.isGet.observeAsState()
    if( isGet.value != true ){
        viewModel.getAll()
    }else {
        val evaluationAnalysis = viewModel.evaluationAnalysis.observeAsState()
        val subscriberList = viewModel.subscriberList.observeAsState()
        ActivityReflectContent(evaluationAnalysis.value, subscriberList.value){
            navController.navigate("organization")
        }
    }
}

@Composable
fun ActivityReflectContent(
    anaylsis:EvaluationAnalysis?,
    subscriberList:ArrayList<UserInfo>?,
    clickEvent:()->Unit
){
    Scaffold(
        topBar = {
            OriganizationTopBar(clickEvent)
        }
    ){ paddingValues ->
        if(anaylsis == null || subscriberList == null){
            Loader(paddingValues)
        }else {
            LazyColumn() {
                item{
                    ImageDisplay(text = "评论词云", img = anaylsis.cloud)
                }
                item{
                    ImageDisplay(text = "情感分析", img = anaylsis.emo_ANALYSIS)
                }
                item{
                    SubscriberDisplay(subscriberList)
                }
            }
        }
    }

}

@Composable
private fun Loader(
    paddingValues: PaddingValues
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center)
        )
    }
}


@Composable
fun ImageDisplay(
    text:String,
    img:String
){
    Surface(
        shape = MaterialTheme.shapes.medium, // 使用 MaterialTheme 自带的形状
        elevation = 5.dp,
        modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 15.dp)
            .fillMaxWidth()
    ) {
        Box(){
            Column() {
                testTitle(text)
//                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high){
//                    Text(
//                        text = text,
//                        color = Color.Black,
//                        style = MaterialTheme.typography.h5,
//                        modifier = Modifier.padding(20.dp, 8.dp, 0.dp, 0.dp),
//                        fontFamily = FontFamily.SansSerif
//                    )
//                }
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .size(600.dp, 300.dp)
                        .padding(15.dp, 5.dp),
                    painter = rememberImagePainter(img),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }
        }
    }

}


@Composable
fun SubscriberDisplay(
    subscriberList: ArrayList<UserInfo>
){
    Box(){
        Column() {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high){
                Text(
                    text = "报名用户",
                    color = Color.Black,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(20.dp, 8.dp, 0.dp, 0.dp),
                    fontFamily = FontFamily.SansSerif
                )
            }
            subscriberList.forEach{ it->
                Box(
                    modifier = Modifier
                        .size(550.dp, 85.dp)
                        .padding(20.dp, 0.dp)
                        .clickable { }
                        .background(Color.White)
                ){
                    Row(modifier = Modifier.padding(all = 8.dp)) {
                        Image(
                            painter = rememberImagePainter(it.avator),
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
                                text = it.user_NAME,
                                color = Color.Black,
                                style = MaterialTheme.typography.h6,
                                modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = it.id,
                                color = Color(0xFF7A7A7A),
                                style = MaterialTheme.typography.caption
                            )
                        }
                        Spacer(modifier = Modifier.width(15.dp))
//                        IconButton(onClick = { /*TODO*/ }) {
//                            Icon(Icons.Filled.Add,null)
//                        }
                    }
                }
            }
        }
    }

}


@Composable
fun testTitle(
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