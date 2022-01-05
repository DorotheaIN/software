package com.example.yike.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.yike.*
import com.example.yike.viewModel.GlobalViewModel


@Composable
fun findBackPasswordScreen(
    navController: NavController
){
    findBackPasswordScreenContent(navController)

}



@Composable
fun findBackPasswordScreenContent(
    navController: NavController
){

    val emailInput = remember { EmailState() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xB2806FA0), Color(0xE14256C4)),
                    start = Offset(0f, Float.POSITIVE_INFINITY),
                    end = Offset(Float.POSITIVE_INFINITY, 0f)
                )
            ),
    ) {
        Box(Modifier.align(Alignment.TopStart)) {
            RegisterTable(navController)
        }
        Box(
            Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 22.dp, vertical = 100.dp)
            ) {

                Spacer(Modifier.height(50.dp))
                RegistDescript()
                Spacer(Modifier.height(30.dp))
                TextEmail(emailInput)
                Spacer(Modifier.height(10.dp))
                VerifyButton(
                    onClick = {
//                        if (emailInput.isValid) {
//                            clickEvent(emailInput.text)
//                            GlobalViewModel.updateEmail(emailInput.text)
//                        }
                    }
                )

            }
        }
    }

}

@Composable
private fun RegisterTable(navController: NavController){
    TextButton(onClick = {
        navController.popBackStack()
    }) {
        Text(
            text = "返回",
            fontSize =20.sp,
            color = Color(0xFFFFFFFF),
            style = MaterialTheme.typography.button,
            modifier = Modifier.padding(start = 8.dp,end = 8.dp,top = 8.dp)
        )
    }
}

@Composable
private fun TextEmail(emailInput:EmailState){
//    var text by remember{ mutableStateOf("") }

    val textState = remember {
        EmailState()
    }

    Surface(
        shape = RoundedCornerShape(30.dp),
        color = Color(0x51E4DFDB),
        modifier = Modifier
            .size(width = 700.dp, height = 55.dp)
            .padding(start = 30.dp, end = 30.dp)
            .fillMaxWidth()
    ) {
        TextField(
            value = textState.text,
            onValueChange = { newString ->
                textState.text = newString
                emailInput.text = textState.text
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color(0xFF0D0D0E),
                backgroundColor = Color.Transparent,
                cursorColor = Color(0xFF045DA0),
            ),
            maxLines = 1,
            placeholder = { Text("请输入邮箱号找回密码",
                modifier = Modifier
//                    .padding(start = 75.dp ,end =50.dp )
                    .fillMaxWidth(),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
            ) },
//            shape = RoundedCornerShape(30.dp)
        )
    }
}

@Composable
private fun VerifyButton(
//    navController: NavController,
//                 emailInput: EmailState,
    onClick: () -> Unit
){

    Surface(
        shape = RoundedCornerShape(30.dp),
        color = Color(0xFFFFFFFF),
        modifier = Modifier
            .size(width = 700.dp, height = 55.dp)
            .padding(start = 30.dp, end = 30.dp)
            .fillMaxWidth()
            .clickable(
                onClick = onClick
            )
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("点击发送邮件找回密码",
                modifier = Modifier
                    .padding(vertical = 15.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                style = MaterialTheme.typography.button,
                color = Color(0xFF0D0D0E),
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }

    }
}

