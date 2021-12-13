package com.example.yike

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Face
import androidx.compose.material.icons.sharp.NoteAdd
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.yike.view.RegisterButton
import com.example.yike.viewModel.OfficialRegisterViewModel


@Composable
fun RegisterOfficialScreen(
    navController: NavController,
    officialRegisterViewModel: OfficialRegisterViewModel){

    val officialRegister = officialRegisterViewModel.officialRegisterInfo.observeAsState()

    RegisterOfficialScreenContent(navController){
        avator, certification, introduction, password, userName ->  officialRegisterViewModel.checkOfficialRegisterStatus(avator, certification, introduction, password, userName)
    }
}

@Composable
fun RegisterOfficialScreenContent(
    navController: NavController,
    clickEvent:(avator:String,certification:String,introduction:String,password:String,userName:String) ->Unit
){
    val officialNameInput = remember { NameInputState() }
    val officialCodeInput = remember { PasswordInputState() }
    val officialIntroInput = remember { NameInputState() }
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
        OfficialRegisterTable(navController)
        Spacer(Modifier.height(70.dp))
        RegistOfficialDescript()
        Spacer(Modifier.height(50.dp))
        OfficialTextName(officialNameInput)
        Spacer(Modifier.height(10.dp))
        OfficialTextCode(officialCodeInput)
        Spacer(Modifier.height(10.dp))
        TextIntro(officialIntroInput)
        Spacer(Modifier.height(10.dp))
        UploadPicFile()
        Spacer(Modifier.height(10.dp))
        OfficialRegisterButton(
            onClick = {
                if(officialNameInput.isValid && officialCodeInput.isValid && officialNameInput.isValid){
                    clickEvent("","",officialIntroInput.text,officialCodeInput.text,officialNameInput.text)
                }
            }
        )
    }
}

@Composable
fun OfficialRegisterTable(navController: NavController){
    TextButton(onClick = {
        navController.navigate("personRegister_screen")
    }) {
        Text(
            text = "个人用户注册",
            fontSize =20.sp,
            color = Color(0xFFFFFFFF),
            style = MaterialTheme.typography.button,
            modifier = Modifier.padding(start = 8.dp,end = 8.dp,top = 8.dp)
        )
    }
}

@Preview
@Composable
fun RegistOfficialDescript(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            "官方组织注册",
            color = Color(0xFFFFFFFF),
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 100.dp, end = 80.dp)
        )
    }
}

@Composable
fun OfficialTextName(nameInput:NameInputState){
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
            placeholder = { Text("请输入组织名称",
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
fun OfficialTextCode(passwordInput:PasswordInputState){
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
fun TextIntro(officialIntroInput:NameInputState){
    val textIntro = remember {
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
            value = textIntro.text,
            onValueChange = {newText ->
                textIntro.text = newText
                officialIntroInput.text = textIntro.text
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color(0xFF0D0D0E),
                backgroundColor = Color.Transparent,
                cursorColor = Color(0xFF045DA0),
            ),
            maxLines = 1,
            placeholder = { Text("请输入简介",
                modifier = Modifier
                    .padding(start = 115.dp, end = 50.dp)
                    .fillMaxWidth()
                ,
                color = Color(0xFFFFFFFF)
            ) },
            shape = RoundedCornerShape(30.dp)
        )
    }
}

@Preview
@Composable
fun UploadPicFile(){
Row(
    modifier = Modifier
        .fillMaxWidth()
        .padding(start = 120.dp, end = 100.dp)
) {
    IconButton(onClick = { /*TODO*/ }) {
        Icon(
            Icons.Sharp.Face,
            contentDescription = "uploadPic",
            modifier = Modifier
                .size(250.dp)
        )
    }
    Box(modifier = Modifier.width(70.dp))
    IconButton(onClick = { /*TODO*/ }) {
        Icon(
            Icons.Sharp.NoteAdd,
            contentDescription = "uploadFile",
            modifier = Modifier
                .size(250.dp)
        )
    }
}
}


@Composable
fun OfficialRegisterButton(
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