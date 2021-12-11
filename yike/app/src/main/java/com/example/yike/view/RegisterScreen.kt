package com.example.yike

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.yike.view.ResgisterTwoScreen

@Preview
@Composable
fun RegisterUI(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "personRegister_screen",
    ){
        composable("personRegister_screen"){
            ResgisterScreen(navController = navController)
        }
        composable("officialRegister_screen"){
            ResgisterOfficialScreen(navController = navController)
        }
        composable("personRegister2_screen"){
            ResgisterTwoScreen(navController = navController)
        }
    }
}


@Composable
fun ResgisterScreen(navController: NavController){
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
        RegisterTable(navController)
        Spacer(Modifier.height(70.dp))
        RegistDescript()
        Spacer(Modifier.height(50.dp))
        TextInfomation()
        Spacer(Modifier.height(10.dp))
        VerifyButton(navController)
    }
}


@Composable
fun RegisterTable(navController: NavController){
    TextButton(onClick = {
        navController.navigate("officialRegister_screen")
    }) {
        Text(
            text = "官方组织注册",
            fontSize =20.sp,
            color = Color(0xFFFFFFFF),
            style = MaterialTheme.typography.button,
            modifier = Modifier.padding(start = 8.dp,end = 8.dp,top = 8.dp)
        )
    }
}

@Composable
fun RegistDescript(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            "开启我的大学一刻",
            color = Color(0xFFFFFFFF),
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 60.dp, end = 60.dp)
        )
    }
}

@Composable
fun TextInfomation(){
    var text by remember{ mutableStateOf("") }
    Surface(
        shape = RoundedCornerShape(30.dp),
        color = Color(0x51E4DFDB),
        modifier = Modifier
            .size(width = 700.dp,height = 51.dp)
            .padding(start = 30.dp,end = 30.dp)
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
            placeholder = { Text("请输入邮箱号进行注册",
                modifier = Modifier
                    .padding(start = 75.dp ,end =50.dp )
                    .fillMaxWidth(),
                color = Color(0xFFFFFFFF)
                ) },
//            shape = RoundedCornerShape(30.dp)
        )
    }
}

@Composable
fun VerifyButton(navController: NavController){

    Surface(
        shape = RoundedCornerShape(30.dp),
        color = Color(0xFFFFFFFF),
        modifier = Modifier
            .size(width = 700.dp,height = 50.dp)
            .padding(start = 30.dp,end = 30.dp)
            .fillMaxWidth()
            .clickable(
                onClick = {
                    navController.navigate("personRegister2_screen")
                }
            )
    ) {
        Text("获取邮箱验证码",
            modifier = Modifier
                .padding(start = 105.dp ,end =90.dp ,top = 15.dp,bottom = 15.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            style =MaterialTheme.typography.button,
            color = Color(0xFF0D0D0E),
            fontSize = 18.sp
        )
    }
}