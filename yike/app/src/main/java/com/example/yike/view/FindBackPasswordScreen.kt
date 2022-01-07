package com.example.yike.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
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
import com.example.yike.viewModel.EditPasswordViewModel
import com.example.yike.viewModel.GlobalViewModel
import com.example.yike.viewModel.SendEmailViewModel
import com.example.yike.viewModel.VerifyCodeViewModel


@Composable
fun findBackPasswordScreen(
    sendEmailViewModel: SendEmailViewModel,
    verifyCodeViewModel:VerifyCodeViewModel,
    editPasswordViewModel: EditPasswordViewModel,
    navController: NavController
){

    val sendEmailInfo = sendEmailViewModel.sendEmailInfo.observeAsState()
    val inputVerifyCode = verifyCodeViewModel.inputVerifyCode.observeAsState()
    val getEditInfo = editPasswordViewModel.getEditInfo.observeAsState()


    var openDialog: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }//记录是否打开通过提示框

    alterDialog(openDialog)

    findBackPasswordScreenContent(navController,inputVerifyCode.value,openDialog,
        {
            email ->  sendEmailViewModel.checksendStatus(email)
        },
        {
            code ->  verifyCodeViewModel.verifyCode(code)
        },
        {
            email, password ->  editPasswordViewModel.sendEditInfo(email, password)
        }
        )
}



@Composable
fun findBackPasswordScreenContent(
    navController: NavController,
    isSuccess:String?,
    openDialog: MutableState<Boolean>,
    sendEmailEvent:(email:String)->Unit,
    verifyEvent:(code:String)->Unit,
    updateEvent:(email:String,password:String)->Unit
){

    val emailInput = remember { EmailState() }
    val passwordInput = remember {PasswordInputState()}
    val verifyCodeInput = remember {VerifyCodeInputState()}


    if(isSuccess=="success"){
        println("2222222isSuccess = $isSuccess")
        updateEvent(emailInput.text,passwordInput.text)
    }else if(isSuccess=="wrong")
    {
        println("2222222isSuccess = $isSuccess")
        openDialog.value = true
    }


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
            registerTable(navController)
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
                textEmail(emailInput)
                Spacer(Modifier.height(10.dp))
                verifyButton(
                    onClick = {
                        if (emailInput.isValid) {
                            sendEmailEvent(emailInput.text)
                        }
                    }
                )
                Spacer(Modifier.height(10.dp))
                textPassword(passwordInput)
                Spacer(Modifier.height(10.dp))
                textVerifyCode(verifyCodeInput)
                Spacer(Modifier.height(10.dp))
                findButton(
                    verifyEvent = {
                        if(verifyCodeInput.isValid){
                            verifyEvent(verifyCodeInput.text)
                        }
                    }
                )
            }
        }
    }

}

@Composable
private fun registerTable(navController: NavController){
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
private fun textEmail(emailInput:EmailState){
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
private fun verifyButton(
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

@Composable
private fun textPassword(passwordInput:PasswordInputState){
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
                passwordInput.text = textState.text
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color(0xFF0D0D0E),
                backgroundColor = Color.Transparent,
                cursorColor = Color(0xFF045DA0),
            ),
            maxLines = 1,
            placeholder = { Text("请输入新密码",
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
private fun textVerifyCode(verifyCodeInput:VerifyCodeInputState){
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
                verifyCodeInput.text = textState.text
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color(0xFF0D0D0E),
                backgroundColor = Color.Transparent,
                cursorColor = Color(0xFF045DA0),
            ),
            maxLines = 1,
            placeholder = { Text("请输入验证码",
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
private fun findButton(
//    navController: NavController,
//                 emailInput: EmailState,
    verifyEvent:()->Unit,
){

    Surface(
        shape = RoundedCornerShape(30.dp),
        color = Color(0xFFFFFFFF),
        modifier = Modifier
            .size(width = 700.dp, height = 55.dp)
            .padding(start = 30.dp, end = 30.dp)
            .fillMaxWidth()
            .clickable(
                onClick = verifyEvent
            )
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("点击修改密码",
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

@Composable
private fun alterDialog(
    openDialog: MutableState<Boolean>,
) {
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = "错误提醒") },
            text = {
                Text(
                    text = "输入验证码有误",
                    style = MaterialTheme.typography.body1
                )
            }, confirmButton = {
                TextButton(onClick = {
                    openDialog.value = false
                }) {
                    Text(text = "确认",
                        color = Color(0xF23F3D38),
                    )
                }
            }, dismissButton = {
                TextButton(onClick = { openDialog.value = false }) {
                    Text(text = "取消",
                        color = Color(0xF23F3D38),
                    )
                }
            })
    }
}

