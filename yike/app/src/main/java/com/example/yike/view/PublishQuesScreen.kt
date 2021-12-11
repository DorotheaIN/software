package com.example.yike

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun Publish(){
    Scaffold(
        Modifier.padding(0.dp),
        topBar = {
            TopAppBar(
                title = {
                    Button(onClick = { },
                        colors = ButtonDefaults.buttonColors(
                            Color(0xFFFFFF),
                            Color(0xFFFFFF)
                        ),
                        elevation = ButtonDefaults.elevation(0.dp,0.dp,0.dp)
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
                        colors = ButtonDefaults.buttonColors(
                            Color(0xFFFFFF),
                            Color(0xFFFFFF)
                        ),
                        elevation = ButtonDefaults.elevation(0.dp,0.dp,0.dp)
                    ){
                        Text("发布问题",
                            color = Color(0xFF1084E0)
                        )
                    }
                }
            )
        },
    ) {
        Column {
            InputQues()
        }
    }
}

@Composable
fun InputQues(){
    var text by remember{ mutableStateOf("") }

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
        textStyle = androidx.compose.ui.text.TextStyle(
            fontSize = 26.sp
        ),
//    label = {
//        Text(text = "hello")
//        },
        placeholder = { Text("输入问题并以问号结尾",
        fontSize = 26.sp
            ) },
//        colors = TextFieldDefaults.textFieldColors(
//            textColor = Color(0xFF0079D3),
    )
}

@Composable
fun InputQuesIntroduction(){
    var text by remember{ mutableStateOf("") }

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
        textStyle = androidx.compose.ui.text.TextStyle(
            fontSize = 16.sp
        ),
//    label = {
//        Text(text = "hello")
//        },
        placeholder = { Text("对问题补充说明（选填）",
            fontSize = 16.sp
        ) },
//        colors = TextFieldDefaults.textFieldColors(
//            textColor = Color(0xFF0079D3),
    )
}