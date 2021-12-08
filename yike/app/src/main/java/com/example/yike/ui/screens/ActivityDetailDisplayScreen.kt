package com.example.yike.ui.screens

import android.app.Activity
import android.graphics.fonts.FontFamily
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
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
import androidx.navigation.NavController
import com.example.yike.R
import com.example.yike.component.ScrollableAppBar
import com.example.yike.data.ActivityDetail
import com.example.yike.data.activityDetailList
import com.example.yike.data.getActivityDetail
import com.example.yike.data.test
import com.google.accompanist.insets.statusBarsHeight


@Composable
fun NaviIcon(navController: NavController){
    Icon(imageVector = Icons.Filled.ArrowBack,
        modifier = Modifier.clickable {
            navController.navigate("activity_screen")
        },
        contentDescription = "ArrowBack",
        tint = Color.White)
}



@Composable
fun ActivityDetailDisplayScreen(id:Int,navController: NavController){
    val result = getActivityDetail(id)
    var activityDetail = test
    if(result != null){
        activityDetail = result
    }
    Scaffold(
        bottomBar = {
            Bottom(activityDetail)
        },
        content = {
            ActivityDetailScreenContent(activityDetail, navController)
        }
    )

}

@Composable
fun ActivityDetailScreenContent(activityDetail: ActivityDetail,navController: NavController){
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
//                items(100) { index ->
//                    Text("I'm item $index", modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp))
//                }
                item {
                    Spacer(modifier = Modifier.height(60.dp))
                }
            }
            ScrollableAppBar(
                title = activityDetail.title,
                navigationIcon = { NaviIcon(navController = navController) },
                scrollableAppBarHeight = 170.dp,
                toolbarOffsetHeightPx = toolbarOffsetHeightPx,
                backgroundImageId = activityDetail.img
            )

        }

    }
}


@Composable
fun Bottom(activityDetail: ActivityDetail){
    Row(
        Modifier
            .background(Color.White)
            .border(1.dp, Color(0xFFE4E4E4), RoundedCornerShape(7.dp))
    ) {
        SubscribeItem(activityDetail)
        LikeItem(activityDetail)
        EvaluateItem(activityDetail)
    }
}

@Composable
fun LikeItem(activityDetail: ActivityDetail){
    var likenum = activityDetail.likeNum
    var selected by remember{mutableStateOf(false)}
    var change by remember{mutableStateOf(false)}
    val buttonSize by animateDpAsState(
        targetValue = if(change) 45.dp else 32.dp
    )
    if(buttonSize == 45.dp) {
        change = false
    }
    Box(
        modifier = Modifier.padding(30.dp,5.dp)
    ){
        Row(){
            IconButton(onClick = {
                likenum = likenum+1
                change = true
                selected = !selected
            }) {
                Icon(painter = painterResource(id = if(selected) R.drawable.like_selected else R.drawable.like),
                    contentDescription = null,
                    modifier = Modifier.size(buttonSize),
                    tint = if(selected) Color.Red else Color.Gray
                )
            }
            Box(){
                Text(
                    text = likenum.toString(),
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                )
            }

        }
    }
}


@Composable
fun EvaluateItem(activityDetail: ActivityDetail){
    var evaluationList = activityDetail.evaluationList
    Box(
        modifier = Modifier.padding(30.dp,5.dp)
    ){
        Row(){
            IconButton(onClick = {
                /////////
            }) {
                Icon(painter = painterResource(id = R.drawable.review),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    tint = Color(0xFFB0B8C0)
                )
            }
            Box(){
                Text(
                    text = evaluationList.size.toString(),
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                )
            }

        }
    }
}


@Composable
fun SubscribeItem(activityDetail: ActivityDetail){
    var subscriberList = activityDetail.subscriberList
    var selected by remember{mutableStateOf(false)}
    var change by remember{mutableStateOf(false)}
    val buttonSize by animateDpAsState(
        targetValue = if(change) 45.dp else 32.dp
    )
    if(buttonSize == 45.dp) {
        change = false
    }
    Box(
        modifier = Modifier.padding(30.dp,5.dp)
    ){
        Row(){
            IconButton(onClick = {
                /////////////////?????????????????
                change = true
                selected = !selected
            }) {
                Icon(painter = painterResource(id = if(selected) R.drawable.collect_selected else R.drawable.collect),
                    contentDescription = null,
                    modifier = Modifier.size(buttonSize),
                    tint = if(selected) Color(0xFFEEBB21) else Color.Gray
                )
            }
            Box(){
                Text(
                    text = "报名",
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                )
            }
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
fun BasicInfoDisplay(item:ActivityDetail){
    Box(){
        Column(
            modifier = Modifier.padding(9.dp,3.dp,9.dp,3.dp)
        ) {
            //基本信息
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high){
                Text(
                    text = "基本信息",
                    color = Color.Black,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(20.dp, 8.dp, 0.dp, 0.dp),
                    fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif
                )
            }
            Row() {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium){
                    Image(
                        painter = painterResource(R.drawable.time),
                        contentDescription = "time",
                        modifier = Modifier
                            .size(52.dp, 44.dp)
                            .padding(16.dp, 12.dp, 8.dp, 12.dp)
                    )
                    Text(
                        text = "日期：",
                        color = Color.DarkGray,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                    )
                    Text(
                        text = item.date,
                        color = Color.DarkGray,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                    )
                }
            }
            Row() {
                Image(
                    painter = painterResource(R.drawable.place),
                    contentDescription = "place",
                    modifier = Modifier
                        .size(52.dp, 44.dp)
                        .padding(16.dp, 12.dp, 8.dp, 12.dp)
                )
                Text(
                    text = "地点：",
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                )
                Text(
                    text = item.place,
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                )
            }
            Row() {
                Image(
                    painter = painterResource(R.drawable.tag),
                    contentDescription = "form",
                    modifier = Modifier
                        .size(52.dp, 44.dp)
                        .padding(16.dp, 12.dp, 8.dp, 12.dp)
                )
                Text(
                    text = "形式：",
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                )
                Text(
                    text = item.form,
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Divider(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(start = 18.dp, end = 18.dp),
                //颜色
                color = Color.LightGray,
            )
        }

    }
}

@Composable
fun IntroductionDisplay(item:ActivityDetail){
    Box() {
        Column(
            modifier = Modifier.padding(9.dp,3.dp,9.dp,3.dp)
        ){
            GenresRow(item.genres)
            Text(
                text = item.introduction,
                color = Color.DarkGray,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(20.dp, 8.dp, 20.dp, 0.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Divider(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(start = 18.dp, end = 18.dp),
                //颜色
                color = Color.LightGray,
            )
        }
    }
}

@Composable
fun ContentDisplay(item:ActivityDetail){
    Box() {
        Column(
            modifier = Modifier.padding(9.dp,3.dp,9.dp,3.dp)
        ){
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high){
                Text(
                    text = "活动内容",
                    color = Color.Black,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(20.dp, 8.dp, 0.dp, 0.dp),
                    fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif
                )
            }
            Text(
                text = item.content,
                color = Color.DarkGray,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(20.dp, 8.dp, 20.dp, 0.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Divider(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(start = 18.dp, end = 18.dp),
                //颜色
                color = Color.LightGray,
            )
        }
    }
}

@Composable
fun LighteningDisplay(item: ActivityDetail){
    Box() {
        Column(
            modifier = Modifier.padding(9.dp,3.dp,9.dp,3.dp)
        ){
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high){
                Text(
                    text = "活动亮点",
                    color = Color.Black,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(20.dp, 8.dp, 0.dp, 0.dp),
                    fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif
                )
            }
            Text(
                text = item.lightening,
                color = Color.DarkGray,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(20.dp, 8.dp, 20.dp, 0.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Divider(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(start = 18.dp, end = 18.dp),
                //颜色
                color = Color.LightGray,
            )
        }
    }
}

@Composable
fun GenresRow(genres:String){
    val list = genres.split(',')
    LazyRow(contentPadding = PaddingValues(8.dp,8.dp,8.dp,0.dp)){
        items(list){ it->
            Text(
                text = it,
                color = Color(0xFF5B96FC),
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .padding(12.dp, 0.dp, 0.dp, 0.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color(0xFFD0E7FF))
                    .padding(7.dp, 4.dp)
            )
        }
    }
}


@Composable
fun InfoDisplay(item:ActivityDetail){
    IntroductionDisplay(item)
    BasicInfoDisplay(item)
    ContentDisplay(item)
    LighteningDisplay(item)
}

@Composable
fun IntroductionDisplayPrview(){
    val item = test
    Box() {
        Column(){
            GenresRowPreview()
            Text(
                text = item.introduction,
                color = Color.DarkGray,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(20.dp, 8.dp, 20.dp, 0.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Divider(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(start = 18.dp, end = 18.dp),
                //颜色
                color = Color.LightGray,
            )
        }
    }
}



@Composable
fun BasicInfoDisplayPreview(){
    val item = test
    Box(){
        Column(
            modifier = Modifier.padding(9.dp)
        ) {
            //基本信息
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high){
                Text(
                    text = "基本信息",
                    color = Color.Black,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(20.dp, 8.dp, 0.dp, 0.dp),
                    fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif
                )
            }
            Row() {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium){
                    Image(
                        painter = painterResource(R.drawable.time),
                        contentDescription = "time",
                        modifier = Modifier
                            .size(52.dp, 44.dp)
                            .padding(16.dp, 12.dp, 8.dp, 12.dp)
                    )
                    Text(
                        text = "日期：",
                        color = Color.DarkGray,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                    )
                    Text(
                        text = item.date,
                        color = Color.DarkGray,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                    )
                }
            }
            Row() {
                Image(
                    painter = painterResource(R.drawable.place),
                    contentDescription = "place",
                    modifier = Modifier
                        .size(52.dp, 44.dp)
                        .padding(16.dp, 12.dp, 8.dp, 12.dp)
                )
                Text(
                    text = "地点：",
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                )
                Text(
                    text = item.place,
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                )
            }
            Row() {
                Image(
                    painter = painterResource(R.drawable.tag),
                    contentDescription = "form",
                    modifier = Modifier
                        .size(52.dp, 44.dp)
                        .padding(16.dp, 12.dp, 8.dp, 12.dp)
                )
                Text(
                    text = "形式：",
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                )
                Text(
                    text = item.form,
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Divider(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(start = 18.dp, end = 18.dp),
                //颜色
                color = Color.LightGray,
            )
        }

    }
//    Surface(
//        shape = MaterialTheme.shapes.medium, // 使用 MaterialTheme 自带的形状
//        elevation = 5.dp,
//        modifier = Modifier
//            .padding(0.dp, 7.dp)
//            .fillMaxWidth()
//    ){
//
//    }
}

@Composable
fun ContentDisplayPreview(){
    val item = test
    Box() {
        Column(
            modifier = Modifier.padding(9.dp)
        ){
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high){
                Text(
                    text = "活动内容",
                    color = Color.Black,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(20.dp, 8.dp, 0.dp, 0.dp),
                    fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif
                )
            }
            Text(
                text = item.content,
                color = Color.DarkGray,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(20.dp, 8.dp, 20.dp, 0.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Divider(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(start = 18.dp, end = 18.dp),
                //颜色
                color = Color.LightGray,
            )
        }
    }
}




@Composable
fun LighteningDisplayPreview(){
    val item = test
    Box() {
        Column(
            modifier = Modifier.padding(9.dp)
        ){
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high){
                Text(
                    text = "活动亮点",
                    color = Color.Black,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(20.dp, 8.dp, 0.dp, 0.dp),
                    fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif
                )
            }
            Text(
                text = item.lightening,
                color = Color.DarkGray,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(20.dp, 8.dp, 20.dp, 0.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Divider(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(start = 18.dp, end = 18.dp),
                //颜色
                color = Color.LightGray,
            )
        }
    }
}



@Composable
fun GenresRowPreview(){
    val list = test.genres.split(',')
    LazyRow(contentPadding = PaddingValues(12.dp,8.dp,8.dp,0.dp)){
        items(list){ it->
            Text(
                text = it,
                color = Color(0xFF5C63E6),
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .padding(8.dp, 0.dp, 0.dp, 0.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color(0xFFE1E3FD))
                    .padding(7.dp, 4.dp)
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

