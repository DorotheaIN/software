package com.example.yike.ui.screens


import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import coil.compose.rememberImagePainter
import com.example.yike.R
import com.example.yike.component.RequiredInputState
import com.example.yike.component.ScrollableAppBar
import com.example.yike.viewModel.Activity
import com.example.yike.viewModel.ActivityDetail
import com.example.yike.viewModel.ActivityDetailViewModel
import com.example.yike.viewModel.Evaluation
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun NaviIcon(navController: NavController,activityDetailViewModel: ActivityDetailViewModel,like:Boolean,subscribe:Boolean){
    Icon(imageVector = Icons.Filled.ArrowBack,
        modifier = Modifier.clickable {
            activityDetailViewModel.save(like,subscribe)
            navController.navigate("activity")
        },
        contentDescription = "ArrowBack",
        tint = Color.White
    )
}


@ExperimentalMaterialApi
@Composable
fun ActivityDetailDisplayScreen(
    navController: NavController,
    viewModel: ActivityDetailViewModel
){
    val isGet = viewModel.isGet.observeAsState()
    if( isGet.value != true){
        viewModel.getActivityDetail()
    }else {
        val likeStatus = viewModel.likeStatus.observeAsState()
        val subscribeStatus = viewModel.subscribeStatus.observeAsState()
        val activityDetail = viewModel.activityDetail.observeAsState()
        val evaluationList = viewModel.evaluationList.observeAsState()
//        val activityRecommendedList = viewModel.activityRecommendedList.observeAsState()
        val reviewRes = viewModel.reviewRes.observeAsState()
        val likeRes = viewModel.likeRes.observeAsState()
        val subRes = viewModel.subRes.observeAsState()
        val delRes = viewModel.delReviewRes.observeAsState()
        val userID = viewModel.getUserID
        ActivityDetailScreenContent(activityDetail.value,userID,navController,evaluationList.value,
            likeStatus.value,subscribeStatus.value,viewModel,
            { like,subscribe->
                viewModel.save(like,subscribe)
                navController.navigate("activity")
            },
            { text->
                viewModel.review(text)
            },
            {
                viewModel.deleteReview()
            }
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun ActivityDetailScreenContent(
    activityDetail: ActivityDetail?,
    id:String?,
    navController: NavController,
    evaluationList:ArrayList<Evaluation>?,
//    activityList:ArrayList<Activity>?,
    likeStatus: Boolean?,
    subscribeStatus: Boolean?,
    activityDetailViewModel: ActivityDetailViewModel,
    clickEvent:(like:Boolean,subscribe:Boolean) -> Unit,
    reviewEvent: (text:String) -> Unit,
    delReviwEvent:() -> Unit
){
    val likeSelected = remember{mutableStateOf(false)}
    val subscribeSelected = remember{mutableStateOf(false)}
    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetState = state,
        sheetContent = {
            ReviewTextField(reviewEvent){
                scope.launch { state.hide() }
            }
        }
    ) {
        Scaffold(
            bottomBar = {
                if(activityDetail!=null){
                    if(likeStatus!=null && subscribeStatus!=null){
                        likeSelected.value = likeStatus
                        subscribeSelected.value = subscribeStatus
                        Bottom(activityDetail,evaluationList,likeSelected,subscribeSelected){
                            scope.launch { state.show() }
                        }
                    }
                }
            },
            content = {
                if(activityDetail!=null && id != null){
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
//                                if(activityList!=null){
//                                    item{
//                                        ActivityRecommendedDisplay(activityList)
//                                    }
//                                }
                                if(evaluationList != null){
                                    item{
                                        EvaluationDiplay(evaluationList,id,delReviwEvent)
                                    }
                                }
                                item {
                                    Spacer(modifier = Modifier.height(70.dp))
                                }
                            }
                            ScrollableAppBar(
                                title = activityDetail.title,
                                navigationIcon = { NaviIcon(navController = navController,activityDetailViewModel,likeSelected.value,subscribeSelected.value) },
                                scrollableAppBarHeight = 170.dp,
                                toolbarOffsetHeightPx = toolbarOffsetHeightPx,
                                backgroundImg = activityDetail.img
                            )

                        }

                    }
                }
            }
        )
    }
}


@ExperimentalMaterialApi
@Composable
fun Bottom(
    activityDetail: ActivityDetail,
    evaluationList: ArrayList<Evaluation>?,
    likeStatus:MutableState<Boolean>,
    subscribeStatus:MutableState<Boolean>,
    clickEvent: () -> Unit = {},
){
    Row(
        Modifier
            .background(Color.White)
//            .border(1.dp, Color(0xFFE4E4E4), RoundedCornerShape(7.dp))
            .border(1.dp, Color(0xFFE4E4E4))
            .fillMaxWidth()
    ) {
        SubscribeIcon(activityDetail,subscribeStatus)
        LikeIcon(activityDetail,likeStatus)
        if(evaluationList != null){
            EvaluateIcon(evaluationList.size,clickEvent)
        }else {
            EvaluateIcon(0,clickEvent)
        }
    }

}

@Composable
fun LikeIcon(activityDetail: ActivityDetail,selected:MutableState<Boolean>){
    val likenum = remember{mutableStateOf(activityDetail.likeNum)}
    val change = remember{mutableStateOf(false)}
    val buttonSize by animateDpAsState(
        targetValue = if(change.value) 45.dp else 32.dp
    )
    if(buttonSize == 45.dp) {
        change.value = false
    }
    Box(
        modifier = Modifier.padding(30.dp,5.dp)
    ){
        Row(){
            IconButton(onClick = {
                if(selected.value == true){
                    likenum.value = likenum.value-1
                }else{
                    likenum.value = likenum.value+1
                }
                change.value = true
                selected.value = !selected.value
            }) {
                Icon(painter = painterResource(id = if(selected.value) R.drawable.like_selected else R.drawable.like),
                    contentDescription = null,
                    modifier = Modifier.size(buttonSize),
                    tint = if(selected.value) Color.Red else Color.Gray
                )
            }
            Box(){
                Text(
                    text = likenum.value.toString(),
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                )
            }

        }
    }
}

@Composable
fun EvaluateIcon(
    evaluationNum:Int,
    clickEvent:()->Unit
){
    Box(
        modifier = Modifier.padding(30.dp,5.dp)
    ){
        Row(){
            IconButton(onClick = clickEvent ) {
                Icon(painter = painterResource(id = R.drawable.review),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    tint = Color(0xFFB0B8C0)
                )
            }
            Box(){
                Text(
                    text = evaluationNum.toString(),
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                )
            }

        }
    }
}


@Composable
fun SubscribeIcon(activityDetail: ActivityDetail,selected:MutableState<Boolean>){
    var subscriberList = activityDetail.subscriberNum
//    var selected by remember{mutableStateOf(status)}
    var change by remember{mutableStateOf(false)}
    val buttonSize by animateDpAsState(
        targetValue = if(change) 45.dp else 32.dp
    )
    if(buttonSize == 45.dp) {
        change = false
    }
    Box(
        modifier = Modifier.padding(20.dp,5.dp,15.dp,5.dp)
    ){
        Row(){
            IconButton(onClick = {
                change = true
                selected.value = !selected.value
            }) {
                Icon(painter = painterResource(id = if(selected.value) R.drawable.collect_selected else R.drawable.collect),
                    contentDescription = null,
                    modifier = Modifier.size(buttonSize),
                    tint = if(selected.value) Color(0xFFEEBB21) else Color.Gray
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
fun PictureItem(item: Activity){
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
            painter = rememberImagePainter(item.img),
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

//qi
@Composable
fun ActivityRecommendedDisplay(activityList: ArrayList<Activity>){
    Box() {
        Column(
            modifier = Modifier.padding(9.dp,3.dp,9.dp,3.dp)
        ){
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high){
                Text(
                    text = "推荐活动",
                    color = Color.Black,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(20.dp, 8.dp, 0.dp, 0.dp),
                    fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif
                )
            }
            LazyRow(Modifier){
                activityList.forEach{it->
                    item {
                        PictureItem(it)
                    }
                }
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
}


@Composable
fun EvaluationDiplay(
    evaluationList: ArrayList<Evaluation>,
    id: String,
    delReviwEvent:() -> Unit
){
    Column() {
        evaluationList.forEach{it->
            if(it.reviewerID == id){
                EvaluationItem(it,true,delReviwEvent)
            }else{
                EvaluationItem(it,false,delReviwEvent)
            }
        }
    }
}

@Composable
fun EvaluationItem(
    evaluation:Evaluation,
    deletable:Boolean,
    delReviwEvent:() -> Unit
){
    Box(
        modifier = Modifier
            .size(550.dp, 85.dp)
            .padding(20.dp, 0.dp,0.dp,0.dp)
            .clickable { }
            .background(Color.White)
    ){
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = rememberImagePainter(evaluation.reviewerAvator),
                contentDescription = null,
                modifier = Modifier
                    .padding(0.dp, 5.dp)
                    .size(55.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxSize()
            )
            Spacer(modifier = Modifier.width(15.dp))

            Column (modifier = Modifier.size(250.dp,65.dp).clickable {  }){
                Text(
                    text = evaluation.reviewerName,
                    color = Color.Black,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = evaluation.content,
                    color = Color(0xFF7A7A7A),
                    style = MaterialTheme.typography.caption
                )
            }
            if(deletable){
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {
                    IconButton(
                        onClick = delReviwEvent
                    ) {
                        Icon(Icons.Outlined.Delete,null)
                    }
                }
            }
        }
    }
}

@Composable
fun ReviewTextField(
    reviewEvent: (text: String) -> Unit,
    closeEvent: () ->Unit
){
    val state = remember { RequiredInputState() }
    Box(
        modifier = Modifier
            .size(600.dp, 200.dp)
            .fillMaxWidth()
            .background(Color(0xFFD3D3D3)),
    ) {
        BasicTextField(
            value = state.text,
            onValueChange = {
                state.text = it
            },
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize(),
            decorationBox = { innerTextField ->
                Column(
                    modifier = Modifier
                        .padding(10.dp, 15.dp, 0.dp, 5.dp)
                        .fillMaxSize()
                ) {
                    Row(
                        modifier = Modifier.padding(0.dp,5.dp),
                        horizontalArrangement = Arrangement.Start,
//                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        IconButton(onClick = {},Modifier.size(15.dp)) { Icon(painterResource(id = R.drawable.more), contentDescription = null) }
                        IconButton(onClick = {},Modifier.size(15.dp)) { Icon(painterResource(id = R.drawable.more), contentDescription = null) }
                        IconButton(onClick = {},Modifier.size(15.dp)) { Icon(painterResource(id = R.drawable.more), contentDescription = null) }
                        IconButton(onClick = {},Modifier.size(15.dp)) { Icon(painterResource(id = R.drawable.more), contentDescription = null) }
                    }
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .fillMaxSize()
                            .weight(1f)
                    ) {
                        innerTextField()
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
//                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        TextButton( onClick = {
                            reviewEvent(state.text)
                            closeEvent()
                        }) {
                            Text("发送")
                        }
                        Spacer(Modifier.padding(horizontal = 10.dp))
                        TextButton( onClick = closeEvent ) {
                            Text("关闭")
                        }
                    }
                }
            }
        )
    }

}



@Composable
fun displayTextStyle(){
    Column() {
        Text(
            text = "枝枝",
            color = Color.Black,
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
        )
        Text(
            text = "枝枝",
            color = Color.Black,
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
        )
        Text(
            text = "枝枝",
            color = Color.Black,
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
        )
        Text(
            text = "枝枝",
            color = Color.Black,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
        )
        Text(
            text = "枝枝",
            color = Color.Black,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
        )
        Text(
            text = "枝枝",
            color = Color.Black,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
        )
        Text(
            text = "枝枝",
            color = Color.Black,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
        )
        Text(
            text = "枝枝",
            color = Color.Black,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
        )
        Text(
            text = "枝枝",
            color = Color.Black,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
        )
        Text(
            text = "枝枝",
            color = Color.Black,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
        )
        Text(
            text = "枝枝",
            color = Color.Black,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
        )
        Text(
            text = "枝枝",
            color = Color.Black,
            style = MaterialTheme.typography.overline,
            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
        )
        Text(
            text = "枝枝",
            color = Color.Black,
            style = MaterialTheme.typography.button,
            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
        )

    }

}