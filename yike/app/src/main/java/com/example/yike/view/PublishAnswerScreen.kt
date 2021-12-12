package com.example.yike

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.ButtonDefaults.elevation
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.yike.data.AnswerData




@Composable
fun AnswerScreen(navController: NavController){
    Scaffold(
        Modifier.padding(0.dp),
        topBar = {
            TopAppBar(
                title = {
                    Button(onClick = { },
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
                    Button(onClick = { },
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
//            questionPart(ans = AnswerData)
            AnswerPart()
        }
    }
}

//@Composable
//fun questionPart(ans:Ques)
//{
//    Column() {
//        Spacer(modifier = Modifier.padding(vertical = 6.dp))
//        Row {
//            Box(Modifier.padding(horizontal = 4.dp))
//            Text(
//                text = ans.answer.question,
//                fontSize = 20.sp,
//            )
//        }
//        Spacer(modifier = Modifier.height(10.dp))
//            Divider(
//                Modifier
//                    .fillMaxWidth()
//                    .height(1.dp)
//                    .padding(start = 0.dp, end = 0.dp),
//                //颜色
//                color = Color.LightGray,
//            )
//    }
//}

@Composable
fun AnswerPart(){
    var text by remember{ mutableStateOf("")}

    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF0D0D0E),
            backgroundColor = Color.Transparent,
            cursorColor = Color(0xFF045DA0),
        ),
//    label = {
//        Text(text = "hello")
//        },
    placeholder = { Text("输入图文回答内容") },
//        colors = TextFieldDefaults.textFieldColors(
//            textColor = Color(0xFF0079D3),
        )

}

//数据