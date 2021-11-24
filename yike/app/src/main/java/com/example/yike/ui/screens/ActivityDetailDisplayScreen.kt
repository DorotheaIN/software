package com.example.yike.ui.screens

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.yike.R
import com.example.yike.component.ScrollableAppBar
import com.example.yike.data.ActivityDetail
import com.example.yike.data.activityDetailList
import com.example.yike.data.getActivityDetail
import com.example.yike.data.test
import com.google.accompanist.insets.statusBarsHeight


@Composable
fun ActivityDetailDisplayScreen(id:Int){
    val result = getActivityDetail(id)
    var activityDetail = test
    if(result != null){
        activityDetail = result
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        // ToolBar 最大向上位移量
        // 56.dp 参考自 androidx.compose.material AppBar.kt 里面定义的 private val AppBarHeight = 56.dp
        val maxUpPx = with(LocalDensity.current) { 170.dp.roundToPx().toFloat() - 56.dp.roundToPx().toFloat() }
        // ToolBar 最小向上位移量
        val minUpPx = 0f
        // 偏移折叠工具栏上移高度
        val toolbarOffsetHeightPx = remember { mutableStateOf(0f) }
        // 现在，让我们创建与嵌套滚动系统的连接并聆听子 LazyColumn 中发生的滚动
        val nestedScrollConnection = remember {
            object : NestedScrollConnection {
                override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                    // try to consume before LazyColumn to collapse toolbar if needed, hence pre-scroll
                    val delta = available.y
                    val newOffset = toolbarOffsetHeightPx.value + delta
                    toolbarOffsetHeightPx.value = newOffset.coerceIn(-maxUpPx, minUpPx)
                    // here's the catch: let's pretend we consumed 0 in any case, since we want
                    // LazyColumn to scroll anyway for good UX
                    // We're basically watching scroll without taking it
                    return Offset.Zero
                }
            }
        }
        Box(
            Modifier
                .fillMaxSize()
                // attach as a parent to the nested scroll system
                .nestedScroll(nestedScrollConnection)
        ) {
            // our list with build in nested scroll support that will notify us about its scroll
            LazyColumn(contentPadding = PaddingValues(top = 170.dp)) {
                item {
                    InfoDisplay(item = activityDetail)
                }
                items(100) { index ->
                    Text("I'm item $index", modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp))
                }

            }
            ScrollableAppBar(
                title = activityDetail.title,
                scrollableAppBarHeight = 170.dp,
                toolbarOffsetHeightPx = toolbarOffsetHeightPx,
                backgroundImageId = activityDetail.img
            )
        }
    }
}



@Composable
fun ActivityHeader(scrollState: LazyListState,title:String) {
    val target = LocalDensity.current.run {
        200.dp.toPx()
    }
    val scrollPercent: Float = if (scrollState.firstVisibleItemIndex > 0){
        1f
    }else {
        scrollState.firstVisibleItemScrollOffset / target
    }
    val activity = LocalContext.current as Activity
    Column() {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsHeight()
                .alpha(scrollPercent)
                .background(Color(0xFF7F6351))
        )
        Box(modifier = Modifier.height(44.dp)){
            Spacer(modifier = Modifier
                .fillMaxSize()
                .alpha(scrollPercent)
                .background(Color(0xFF7F6351))
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.mipmap.icon_back),
                    contentDescription = "back",
                    modifier = Modifier
                        .size(52.dp, 44.dp)
                        .padding(16.dp, 12.dp, 8.dp, 12.dp)
                        .clickable {
                            activity.finish()
                        }
                )
                Text(
                    text = title,
                    color = Color.White,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.alpha(scrollPercent)
                )
            }

        }
    }

}

@Composable
fun ActivityTopItem(item:ActivityDetail){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(170.dp)
    ){
        Image(
            painter = painterResource(item.img),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
        Column(modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF7F6351), Color(0x807F6351)),
                    start = Offset(0f, Float.POSITIVE_INFINITY),
                    end = Offset(Float.POSITIVE_INFINITY, 0f)
                )
            )
        ) {
            Spacer(modifier = Modifier.statusBarsHeight())
            Spacer(modifier = Modifier.height(64.dp))
            Text(
                text = item.title,
                color = Color.White,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(16.dp, 8.dp)
            )
            Text(
                text = item.organizer.name,
                color = Color.White,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(16.dp, 0.dp)
            )
        }
    }
}

@Preview
@Composable
fun ActivityTopItemPreview(){
    val item = test
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(170.dp)){
        Image(
            painter = painterResource(item.img),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF7F6351), Color(0x807F6351)),
                    start = Offset(0f, Float.POSITIVE_INFINITY),
                    end = Offset(Float.POSITIVE_INFINITY, 0f)
                )
            )
        ) {
            Spacer(modifier = Modifier.statusBarsHeight())
            Spacer(modifier = Modifier.height(64.dp))
            Text(
                text = item.title,
                color = Color.White,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(16.dp, 8.dp)
            )
            Text(
                text = item.organizer.name,
                color = Color.White,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(16.dp, 0.dp)
            )
        }
    }
}


@Composable
fun PictureDisplay(item:ActivityDetail){
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
fun InfoDisplay(item:ActivityDetail){
    Surface(
        shape = MaterialTheme.shapes.medium, // 使用 MaterialTheme 自带的形状
        elevation = 5.dp,
        modifier = Modifier
            .padding(0.dp, 7.dp)
            .fillMaxWidth()
    ){
        Box(){
            Column() {
                //基本信息
                Text(
                    text = "基本信息",
                    color = Color.Black,
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                )
                Row() {
                    Text(
                        text = "日期：",
                        color = Color.LightGray,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                    )
                    Text(
                        text = item.date,
                        color = Color.LightGray,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                    )
                }
                Row() {
                    Text(
                        text = "地点：",
                        color = Color.LightGray,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                    )
                    Text(
                        text = item.place,
                        color = Color.LightGray,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                    )
                }
            }
        }
    }
//    Text(text = item.title)
//    Text(text = item.organizer.name)
//    Text(text = item.date)
//    Text(text = item.place)
//    Text(text = item.form)
//    Text(text = item.introduction)
//    Text(text = item.content)
//    Text(text = item.lightening)

}