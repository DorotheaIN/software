package com.example.yike

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.ButtonDefaults.elevation
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.yike.data.AnswerData
import com.example.yike.viewModel.AddAnswerViewModel
import com.example.yike.viewModel.GlobalViewModel


@Composable
fun  AnswerScreen(
    navController: NavController,
    addAnswerViewModel: AddAnswerViewModel
){
    val inputAnswerInfo =addAnswerViewModel.inputAnswerInfo.observeAsState()
    val quetionTitle = addAnswerViewModel.questionTitle
    val questionId = addAnswerViewModel.questionId
//    val answerId = addAnswerViewModel.answerId
    AnswerScreenContent(navController = navController,quetionTitle,questionId){
        content,questionId,userId -> addAnswerViewModel.checkInputAnswer(content, questionId, userId)
    }
}

@Composable
fun AnswerScreenContent(
    navController: NavController,
    questionTitle:String,
    questionId:String,
//    answerId:String,
    clickEvent:(content:String,questionId: String,userId: String) -> Unit,
){
    val answerInput = remember {NameInputState()}
    Scaffold(
        Modifier.padding(0.dp),
        topBar = {
            TopAppBar(
                title = {
                    Button(onClick = {
                        navController.popBackStack()//回退
                    },
                        colors = buttonColors(Color(0xFFFFFF),
                            Color(0xFFFFFF)),
                        elevation = elevation(0.dp,0.dp,0.dp)
                    ){
                        Text("取消",
                            color = Color(0xFFB1A8A1)
                        )
                    }
                },
                backgroundColor = Color(0xFFFFFF),
                contentColor = Color(0xFFFFFF),
                elevation = 0.dp,
                actions = {
                    Button(
                        onClick = {
                            if(answerInput.isValid){
                                GlobalViewModel.getUserInfo()?.let {
                                    clickEvent(answerInput.text,questionId,
                                        it.id)
                                }
//                                navController.navigate("detailed_screen/${questionId}/${answerId}")
                                navController.popBackStack()//回退
                            }
                    },
                        colors = buttonColors(Color(0xFFFFFF),
                            Color(0xFFFFFF)),
                        elevation = elevation(0.dp,0.dp,0.dp)
                    ){
                        Text("发布",
                            color = Color(0xFF1084E0)
                        )
                    }
                }
            )
        },
    ) {
        Column() {
            questionPart(questionTitle)
            TextAnswerPart(answerInput)
        }
    }
}

@Composable
fun questionPart(questionTitle: String)
{
    Column() {
        Spacer(modifier = Modifier.padding(vertical = 6.dp))
        Row {
            Box(Modifier.padding(horizontal = 4.dp))
            Text(
                text = questionTitle,//问题部分
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
fun TextAnswerPart(answerInput:NameInputState){
//    var text by remember{ mutableStateOf("")}
    val textAnswer = remember {
        NameInputState()
    }

    Surface(
        color = Color(0x51E4DFDB),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextField(
            value = textAnswer.text,
            onValueChange = { newString ->
                textAnswer.text = newString
                answerInput.text = textAnswer.text
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color(0xFF0D0D0E),
                backgroundColor = Color.Transparent,
                cursorColor = Color(0xFF045DA0),
            ),
            placeholder = {
                Text(
                    "输入回答内容",
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color(0xFFBBB4B4)
                )
            },
            shape = RoundedCornerShape(30.dp)
        )
    }

}

//数据