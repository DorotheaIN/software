package com.example.yike.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.yike.NameInputState
import com.example.yike.PasswordInputState
import com.example.yike.RegistDescript
import com.example.yike.VerifyCodeInputState
import com.example.yike.viewModel.GetPersonRegisterViewModel
import com.example.yike.viewModel.GlobalViewModel
import com.example.yike.viewModel.GlobalViewModel.sendEmailInfo

@Composable
fun RegisterTwoScreen(
    navController: NavController,
    getPersonRegisterViewModel: GetPersonRegisterViewModel
    ) {
    println("3333333333333333")
    println(sendEmailInfo.value)
    val getPersonRegisterInfo = getPersonRegisterViewModel.personRegisterInfo.observeAsState()
    RegisterTwoScreenContent(navController, GlobalViewModel.getEmail().toString() , verifyCode = GlobalViewModel.getVerifyCode()){
        email,name,password -> getPersonRegisterViewModel.checkPersonRegisterStatus(email,name,password)
    }
}


@Composable
fun RegisterTwoScreenContent(
    navController: NavController,
    email:String,
    verifyCode:String?,
    clickEvent: (email:String,name:String,password:String) -> Unit
    ){

    val nameInput = remember { NameInputState() }
    val passwordInput = remember { PasswordInputState() }
    val verifyCodeInput = remember { VerifyCodeInputState() }
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
        TextName(nameInput)
        Spacer(Modifier.height(10.dp))
        TextCode(passwordInput)
        Spacer(Modifier.height(10.dp))
        TextVerify(verifyCodeInput)
        Spacer(Modifier.height(10.dp))
        RegisterButton(
            onClick = {
                if(passwordInput.isValid && nameInput.isValid && verifyCodeInput.isValid && verifyCodeInput.text == verifyCode){
                    clickEvent(email,nameInput.text,passwordInput.text)
                }
            }
        )
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
fun TextName(nameInput:NameInputState){
    val textName = remember {
        NameInputState()
    }
    Surface(
        shape = RoundedCornerShape(30.dp),
        color = Color(0x51E4DFDB),
        modifier = Modifier
            .size(width = 700.dp, height = 50.dp)
            .padding(start = 30.dp, end = 30.dp)
            .fillMaxWidth()
    ) {
        TextField(
            value = textName.text,
            onValueChange = {newString ->
                textName.text = newString
                nameInput.text = textName.text
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
fun TextCode(passwordInput:PasswordInputState){
    val textCode = remember {
        PasswordInputState()
    }
    Surface(
        shape = RoundedCornerShape(30.dp),
        color = Color(0x51E4DFDB),
        modifier = Modifier
            .size(width = 700.dp, height = 50.dp)
            .padding(start = 30.dp, end = 30.dp)
            .fillMaxWidth()
    ) {
        TextField(
            value = textCode.text,
            onValueChange = {newText ->
                textCode.text = newText
                passwordInput.text = textCode.text
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

@Composable
fun TextVerify(verifyCodeInput:VerifyCodeInputState){
    val textVerifyCode = remember {
        VerifyCodeInputState()
    }
    Surface(
        shape = RoundedCornerShape(30.dp),
        color = Color(0x51E4DFDB),
        modifier = Modifier
            .size(width = 700.dp, height = 50.dp)
            .padding(start = 30.dp, end = 30.dp)
            .fillMaxWidth()
    ) {
        TextField(
            value = textVerifyCode.text,
            onValueChange = {newText ->
                textVerifyCode.text = newText
                verifyCodeInput.text = textVerifyCode.text
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

@Composable
fun RegisterButton(
    onClick: () -> Unit
){
    Surface(
        shape = RoundedCornerShape(30.dp),
        color = Color(0xFFFFFFFF),
        modifier = Modifier
            .size(width = 700.dp, height = 50.dp)
            .padding(start = 30.dp, end = 30.dp)
            .fillMaxWidth()
            .clickable(
                onClick = onClick
            )
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