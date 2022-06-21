package com.example.yike.view

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.yike.*
import com.example.yike.viewModel.*


@Composable
fun findBackPasswordScreen(
    sendEmailViewModel: SimpleVerifyViewModel,
    verifyCodeViewModel:VerifyCodeViewModel,
    editPasswordViewModel: EditPasswordViewModel,
    navController: NavController
){

    val sendEmailInfo = sendEmailViewModel.getTo.observeAsState()
    val inputVerifyCode = verifyCodeViewModel.inputVerifyCode.observeAsState()
    val getEditInfo = editPasswordViewModel.getEditInfo.observeAsState()


    var openDialog: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }//记录是否打开通过提示框

    alterDialog(openDialog)

    findBackPasswordScreenContent(navController,inputVerifyCode.value,openDialog,
        {
            email ->  sendEmailViewModel.sendEmail(email)
        },
//        {
//            code ->  verifyCodeViewModel.verifyCode(code)
//        },
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
//    verifyEvent:(code:String)->Unit,
    updateEvent:(email:String,password:String)->Unit
){

    val emailInput = remember { EmailState() }
    val passwordInput = remember {PasswordInputState()}
    val rePasswordInput = remember{RePasswordInputState()}
    val verifyCodeInput = remember {VerifyCodeInputState()}

    GlobalViewModel.updatePassWord(passwordInput.text)


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

                Spacer(Modifier.height(1.dp))
                FindDescript()
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
                ReTextCode(rePasswordInput)
                Spacer(Modifier.height(10.dp))
                textVerifyCode(verifyCodeInput)
                Spacer(Modifier.height(10.dp))
                findButton(
                    navController
                ) { updateEvent(emailInput.text, passwordInput.text)

                    navController.navigate("login")
                }
//                    verifyEvent = {
//                        if(verifyCodeInput.isValid){
//                            verifyEvent(verifyCodeInput.text)
//                        }
//                    }

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
        PasswordInputState()
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
            visualTransformation = if (textState.shouldHidePassword) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            trailingIcon = {
                Crossfade(targetState = textState.shouldHidePassword) { hidePassword ->
                    if (hidePassword) {
                        PasswordVisabilityIcon(
                            iconToUse = Icons.Default.VisibilityOff,
                            textState = textState
                        )
                    } else {
                        PasswordVisabilityIcon(
                            iconToUse = Icons.Default.Visibility,
                            textState = textState
                        )
                    }
                }
            },
            modifier = Modifier.onFocusChanged {
                val isFocused = it.isFocused
                textState.onFocusChange(isFocused)

                if (!isFocused) {
                    textState.enableShowErrors()
                }
            },
            isError = textState.showErrors,

//            shape = RoundedCornerShape(30.dp)
        )
    }
    textState.getError()?.let { errorMessage ->
        TextFieldError(textError = errorMessage)
    }
}

@Composable
private fun ReTextCode(passwordInput:RePasswordInputState){
    val reTextCode = remember {
        RePasswordInputState()
    }
    Surface(
        shape = RoundedCornerShape(30.dp),
        color = Color(0x51E4DFDB),
        modifier = Modifier
            .size(width = 700.dp, height = 55.dp)
            .padding(start = 30.dp, end = 30.dp)
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = reTextCode.text,
            onValueChange = {newText ->
                reTextCode.text = newText
                passwordInput.text = reTextCode.text
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color(0xFF0D0D0E),
                backgroundColor = Color.Transparent,
                cursorColor = Color(0xFF045DA0),
            ),
            maxLines = 1,
            placeholder = { Text("请再次输入密码确认",
                modifier = Modifier
//                    .padding(start = 115.dp, end = 50.dp)
                    .fillMaxWidth(),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center
            ) },
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier.onFocusChanged {
                val isFocused = it.isFocused
                reTextCode.onFocusChange(isFocused)

                if (!isFocused) {
                    reTextCode.enableShowErrors()
                }
            },
            isError = reTextCode.showErrors,
            visualTransformation = if (reTextCode.shouldHidePassword) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            trailingIcon = {
                Crossfade(targetState = reTextCode.shouldHidePassword) { hidePassword ->
                    if (hidePassword) {
                        RePasswordVisabilityIcon(
                            iconToUse = Icons.Default.VisibilityOff,
                            textState = reTextCode
                        )
                    } else {
                        RePasswordVisabilityIcon(
                            iconToUse = Icons.Default.Visibility,
                            textState = reTextCode
                        )
                    }
                }
            },


            )

    }
    reTextCode.getError()?.let { errorMessage ->
        TextFieldError(textError = errorMessage)
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
    navController: NavController,
    findEvent:()->Unit,
//                 emailInput: EmailState,
//    verifyEvent:()->Unit,
){

    Surface(
        shape = RoundedCornerShape(30.dp),
        color = Color(0xFFFFFFFF),
        modifier = Modifier
            .size(width = 700.dp, height = 55.dp)
            .padding(start = 30.dp, end = 30.dp)
            .fillMaxWidth()
            .clickable(
                onClick = findEvent
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

@Composable
fun FindDescript(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column() {
            Text(
                //"开启我的大学一刻",
                "找回密码",
                color = Color(0xFFFFFFFF),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
//                .padding(start = 60.dp, end = 60.dp)
            )
        }

    }
}

@Composable
private fun PasswordVisabilityIcon(
    iconToUse: ImageVector,
    textState: PasswordInputState
) {
    Icon(
        iconToUse,
        contentDescription = "Toggle Password Visibility",
        modifier = Modifier
            .clickable {
                textState.shouldHidePassword = !textState.shouldHidePassword
            },
    )
}

@Composable
private fun RePasswordVisabilityIcon(
    iconToUse: ImageVector,
    textState: RePasswordInputState
) {
    Icon(
        iconToUse,
        contentDescription = "Toggle Password Visibility",
        modifier = Modifier
            .clickable {
                textState.shouldHidePassword = !textState.shouldHidePassword
            },
    )
}

@Composable
private fun TextFieldError(textError: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp,horizontal = 30.dp)
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = textError,
            modifier = Modifier.fillMaxWidth(),
            style = LocalTextStyle.current.copy(color = MaterialTheme.colors.error)
        )
    }
}
