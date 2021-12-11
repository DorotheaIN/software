package com.example.yike.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.yike.RegistDescript

@Composable
fun ResgisterTwoScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xC84090C5),
                        Color(0xDDC0A02C)
                    ),
                    start = Offset(0f, Float.POSITIVE_INFINITY),
                    end = Offset(Float.POSITIVE_INFINITY, 0f)
                )
            )
    ) {
        RegisterTwoTable(navController)
        Spacer(Modifier.height(70.dp))
        RegistDescript()
        Spacer(Modifier.height(50.dp))
        TextName()
        Spacer(Modifier.height(10.dp))
        TextCode()
        Spacer(Modifier.height(10.dp))
        TextVerify()
        Spacer(Modifier.height(10.dp))
        RegisterButton()
    }
}

@Composable
fun RegisterTwoTable(navController: NavController){
    TextButton(onClick = {
        navController.navigate("personRegister_screen")
    }) {
        Text(
            text = "上一步",
            fontSize =20.sp,
            color = Color(0xFFFFFFFF),
            style = MaterialTheme.typography.button,
            modifier = Modifier.padding(start = 8.dp,end = 8.dp,top = 8.dp)
        )
    }
}

@Composable
fun TextName(){
    var text by remember{ mutableStateOf("") }
    Surface(
        shape = RoundedCornerShape(30.dp),
        color = Color(0x51E4DFDB),
        modifier = Modifier
            .size(width = 700.dp, height = 50.dp)
            .padding(start = 30.dp, end = 30.dp)
            .fillMaxWidth()
    ) {
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
            maxLines = 1,
            placeholder = { Text("请输入名称",
                modifier = Modifier
                    .padding(start = 115.dp, end = 50.dp)
                    .fillMaxWidth(),
                color = Color(0xFFFFFFFF)
            ) },
            shape = RoundedCornerShape(30.dp)
        )
    }
}

@Composable
fun TextCode(){
    var text by remember{ mutableStateOf("") }
    Surface(
        shape = RoundedCornerShape(30.dp),
        color = Color(0x51E4DFDB),
        modifier = Modifier
            .size(width = 700.dp, height = 50.dp)
            .padding(start = 30.dp, end = 30.dp)
            .fillMaxWidth()
    ) {
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
            maxLines = 1,
            placeholder = { Text("请输入密码",
                modifier = Modifier
                    .padding(start = 115.dp, end = 50.dp)
                    .fillMaxWidth(),
                color = Color(0xFFFFFFFF)
            ) },
            shape = RoundedCornerShape(30.dp)
        )
    }
}

@Preview
@Composable
fun TextVerify(){
    var text by remember{ mutableStateOf("") }
    Surface(
        shape = RoundedCornerShape(30.dp),
        color = Color(0x51E4DFDB),
        modifier = Modifier
            .size(width = 700.dp, height = 50.dp)
            .padding(start = 30.dp, end = 30.dp)
            .fillMaxWidth()
    ) {
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
            maxLines = 1,
            placeholder = { Text("请输入邮箱验证码",
                modifier = Modifier
                    .padding(start = 95.dp, end = 50.dp)
                    .fillMaxWidth(),
                color = Color(0xFFFFFFFF)
            ) },
            shape = RoundedCornerShape(30.dp)
        )
    }
}

@Preview
@Composable
fun RegisterButton(){
    Surface(
        shape = RoundedCornerShape(30.dp),
        color = Color(0xFFFFFFFF),
        modifier = Modifier
            .size(width = 700.dp, height = 50.dp)
            .padding(start = 30.dp, end = 30.dp)
            .fillMaxWidth()
//            .clickable()
    ) {
        Text("点击注册",
            modifier = Modifier
                .padding(start = 135.dp, end = 100.dp, top = 15.dp, bottom = 15.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            style =MaterialTheme.typography.button,
            color = Color(0xFF0D0D0E),
            fontSize = 18.sp
        )
    }
}