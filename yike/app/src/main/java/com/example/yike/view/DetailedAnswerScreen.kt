package com.example.yike

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.Icon
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.sharp.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.yike.data.Answer
import com.example.yike.data.AnswerData
import com.example.yike.data.CommentData
import com.example.yike.data.Taylor
import com.example.yike.viewModel.CommentInfo
import com.example.yike.viewModel.DetailedAnswerViewModel
import com.example.yike.viewModel.QuesAnswer

@Composable
fun DetailedScreen(navController: NavController,
                   detailedAnswerViewModel: DetailedAnswerViewModel
                   ) {
//    println("c111111111111111111111111111111111")
//    println(detailedAnswerViewModel.answerId)
//    println(detailedAnswerViewModel.questionId)
    val questionId = detailedAnswerViewModel.questionId
    val answererId = detailedAnswerViewModel.answerId
    detailedAnswerViewModel.slectQuesAnswer(detailedAnswerViewModel.answerId,detailedAnswerViewModel.questionId)
    val quesAnswerInfoList = detailedAnswerViewModel.quesAnswerInfoList.observeAsState()
    DetailAnswer(quesAnswerInfoList.value,questionId,answererId,navController)
}


//@Preview
@Composable
//fun DetailAnswer(questionAnswerInfoList:ArrayList<QuesAnswer>?,navController: NavController)
fun DetailAnswer(
    questionAnswerInfoList:QuesAnswer?,
    questionId:String,
    answererId:String,
    navController: NavController,
    ){
    Scaffold(
        topBar = {
            TopAppBar(
                title={},
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navController.popBackStack()//回退
                            } //do something
                        ) {
                            Icon(Icons.Filled.ArrowBack,
                                null,
                                tint = Color(0xFFB1A8A1)
                            )
                        }
                    },
                backgroundColor = Color(0xFFFFFF),
                contentColor = Color(0xFFFFFF),
                elevation = 0.dp,
                actions = {
                    TextButton(onClick = {
                        val questionId = questionId
                        val questionTitle = questionAnswerInfoList?.question
//                        navController.navigate("publishAnswer_screen/${questionId}/${questionTitle}/${answererId}")
                        navController.navigate("publishAnswer_screen/${questionId}/${questionTitle}")
                    }) {
                        Text("写回答",
                            color = Color(0xFF1084E0)
                            )
                    }
                }
            )
        },
        bottomBar ={
            BottomAppBar(
                backgroundColor = Color(0xFFFDFDFD),
                contentColor = Color(0xFFFFFF),
                elevation = 0.dp,
                contentPadding = PaddingValues(start = 10.dp,end= 5.dp),
                modifier = Modifier.fillMaxWidth(),
            content = {
                ThumbUpButton()
                Box(Modifier.padding(horizontal = 80.dp))
                Collect()
                CommentButton(navController, answererId)
            }
            )
        }
    ) {
            LazyColumn(Modifier) {
//                item {
//                    questionPart(ans = AnswerData)
//                    UserPart()
//                    ShowAnswer(ans = AnswerData)
//                }
                item {
//                item(questionAnswerInfoList) {
//                    if(questionAnswerInfoList!=null) {
//                        questionAnswerInfoList[] {
//                            DetailedQuestionPart(it)
//                            DetailedUserPart(it)
//                            ShowAnswer(it)
//                        }
//                    questionAnswerInfoList?.get(0)?.let { it1 -> DetailedQuestionPart(it1) }
//                    questionAnswerInfoList?.get(0)?.let { it1 -> DetailedUserPart(it1) }
//                    questionAnswerInfoList?.get(0)?.let { it1 -> ShowAnswer(it1) }

                    if (questionAnswerInfoList != null) {
                        DetailedQuestionPart(questionAnswerInfoList)
                        DetailedUserPart(questionAnswerInfoList)
                        ShowAnswer(questionAnswerInfoList)
                    }
                }
//                items(comments) { comment, ->
//                    CommentCard(com= comment)
//                }
//                item(comments) { comment, ->
//                    CommentCard(com= comment)
//                }
                if (questionAnswerInfoList != null) {
                    item(questionAnswerInfoList.comment){
                        questionAnswerInfoList.comment.forEach() {
                            CommentCard(it)
                        }
                    }
                }
                item{
                    Spacer(modifier = Modifier.padding(vertical = 30.dp))
                }
            }

    }
}

@Composable
fun DetailedUserPart(answerer:QuesAnswer){
//    val userData = Taylor
    Surface(
        modifier = Modifier
            .padding(all = 8.dp),
    ) {
        Row(
            modifier = Modifier.padding(all = 8.dp)
        ) {
            Image(
//                painterResource(id = answerer.info.pic),
                rememberImagePainter(answerer.info.pic),//回答者头像
                contentDescription = "profile picture", //这个描述用于无障碍
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            Column {
                Text(
                    text = answerer.info.name,
                    style = MaterialTheme.typography.subtitle2 // 添加 style
                )
                Spacer(modifier = Modifier.padding(vertical = 4.dp))
                Text(
                    text = answerer.info.intro,
                    style = MaterialTheme.typography.body2, // 添加 style
                )
            }
        }
    }
}

@Composable
fun DetailedQuestionPart(ques:QuesAnswer)
{
    Column() {
        Spacer(modifier = Modifier.padding(vertical = 6.dp))
        Row {
            Box(Modifier.padding(horizontal = 4.dp))
            Text(
                text = ques.question,//展示问题内容
                fontSize = 20.sp,
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Divider(
            Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(start = 0.dp, end = 0.dp),
            //颜色
            color = Color.LightGray,
        )
    }
}

@Composable
fun ShowAnswer(ans: QuesAnswer){
Column() {
    Box(modifier = Modifier.padding(vertical = 2.dp))
    Text(
//        text = AnswerData.answer.answerContent,
        text = ans.answer,//展示回答内容
        modifier = Modifier.padding(horizontal = 10.dp),
        style = TextStyle(
            letterSpacing =0.4.sp
        )
        )
}
}

@Composable
fun Collect() {
    var change by remember{ mutableStateOf(false) }
    var flag by remember{ mutableStateOf(false) }

    val buttonSizeVariable = remember {
        Animatable(24.dp, Dp.VectorConverter)
    }

    LaunchedEffect(change) {
        buttonSizeVariable.animateTo(if(change) 32.dp else 24.dp)
    }
    if(buttonSizeVariable.value == 32.dp) {
        change = false
    }
    IconButton(
        onClick = {
            change = true
            flag = !flag
        }
    ) {
        Icon(
            Icons.Rounded.Favorite,
            contentDescription = null,
            modifier = Modifier.size(buttonSizeVariable.value),
            tint = if(flag) Color.Red else Color.Gray
        )
    }
}

@Composable
fun CommentCard(com:CommentInfo){
    var isExpanded by remember { mutableStateOf (false) } // 创建一个能够检测卡片是否被展开的变量

    if(com==null){
        Text(
            text = "目前还没有评论噢"
        )
    }
    else {
        // 创建一个能够根据 isExpanded 变量值而改变颜色的变量
        val surfaceColor by animateColorAsState(
            targetValue = if (isExpanded) Color(0xFFCCCCCC) else MaterialTheme.colors.surface
        )

        Surface(
            shape = MaterialTheme.shapes.medium, // 使用 MaterialTheme 自带的形状
            elevation = 0.dp,
            modifier = Modifier
                .padding(all = 8.dp)
                .fillMaxWidth()
                .clickable { // 添加一个新的 Modifier 扩展方法，可以让元素具有点击的效果
                    isExpanded = !isExpanded // 编写点击的事件内容
                },
            color = surfaceColor
        ) {
            Row(
                modifier = Modifier.padding(all = 8.dp)
            ) {
                Image(
//                painterResource(id = R.drawable.tay),
                    rememberImagePainter(com.info.pic),//评论者头像
                    contentDescription = "profile picture", //这个描述用于无障碍
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                Column {
                    Text(
                        text = com.info.name,//评论者名字
                        style = MaterialTheme.typography.subtitle2 // 添加 style
                    )
                    Spacer(modifier = Modifier.padding(vertical = 4.dp))
                    Text(
                        text = com.content,//评论者内容
                        style = MaterialTheme.typography.body2, // 添加 style
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        // Composable 大小的动画效果
                        modifier = Modifier.animateContentSize()
                    )
                }
            }
            Divider(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(start = 8.dp, end = 8.dp),
                //颜色
                color = Color.LightGray,
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

//@Composable
//fun Conversation(comments: List<Answer>) {
//    LazyColumn {
//        items(comments) { comment, ->
//            CommentCard(com= comment)
//        }
//    }
//}

@Composable
fun ThumbUpButton(){
    Surface(
        shape = RoundedCornerShape(10.dp), // 使用 MaterialTheme 自带的形状
        color = Color(0x252C7DB4),
        modifier = Modifier.padding(10.dp,10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = { /*TODO*/ } ,
                modifier = Modifier
                    .size(50.dp)
                    .padding(0.dp, 1.dp)
                    .clickable { },
            ) {
                Text(
                    "赞同",
                    color = Color(0xFF1084E0),
                )
            }
            Icon(
                Icons.Sharp.ArrowDropDown,
                contentDescription = "dislike",
                modifier = Modifier
                    .size(50.dp)
                    .padding(5.dp, 0.dp)
                    .clickable { },
                tint = Color(0xFF1084E0)
            )
        }
    }
}

@Composable
fun CommentButton(
    navController: NavController,
    answererId: String,
    ){
    IconButton(onClick = {
        navController.navigate("inputComment_Screen/${answererId}")
    }) {
        Icon(
            painterResource(id = R.drawable.comment),
            contentDescription = "Comment",
            modifier = Modifier
                .size(30.dp)
                .padding(5.dp, 0.dp),
            tint = Color(0xFF1084E0)
        )

    }
}





