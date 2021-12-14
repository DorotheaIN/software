package com.example.yike

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
import androidx.compose.ui.input.key.Key.Companion.Sleep
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.yike.view.RegisterTwoScreen
import com.example.yike.viewModel.*
import com.example.yike.viewModel.GlobalViewModel.checksendStatus
import com.example.yike.viewModel.GlobalViewModel.sendEmailInfo
import kotlinx.coroutines.delay

//data class BackStackEntry(val email: String?,val verifyCode:String?)



@Composable
fun RegisterUI(
    start:String
){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = start,
    ){
        composable("personRegister_screen"){
            val viewModel = SendEmailViewModel()
            ResgisterScreen(navController = navController, viewModel)
        }
        composable("officialRegister_screen"){
            val officialRegisterViewModel = OfficialRegisterViewModel()
            RegisterOfficialScreen(navController = navController,officialRegisterViewModel)
        }
        composable(
            route = "personRegister2_screen",
//            arguments = listOf(
//                navArgument("email"){type = NavType.StringType},
//                navArgument("verifyCode"){type = NavType.StringType})
            ){
            val getPersonRegisterViewModel = GetPersonRegisterViewModel()
//            val email = it.arguments?.getString("email")?:""
//            val verifyCode = it.arguments?.getString("verifyCode")?:""
            RegisterTwoScreen(navController = navController,getPersonRegisterViewModel)
        }
    }
}

@Composable
fun ResgisterScreen(navController: NavController,
                    sendEmailViewModel: SendEmailViewModel
                    ){

    val sendEmailInfo = sendEmailViewModel.sendEmailInfo.observeAsState()
    RegisterScreenContent(navController, sendEmailInfo.value){
        email ->  sendEmailViewModel.checksendStatus(email)
    }
}

@Composable
fun RegisterScreenContent(navController: NavController,
                          code: String?,
                           clickEvent: (email: String) -> Unit,
                           ){
    val emailInput = remember { EmailState() }
    if(!code.isNullOrEmpty()) {
        println("5555555")
        GlobalViewModel.updateVerifyCode(code)
        println(code)
        navController.navigate("personRegister2_screen")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xB2806FA0), Color(0xE14256C4)),
                    start = Offset(0f, Float.POSITIVE_INFINITY),
                    end = Offset(Float.POSITIVE_INFINITY, 0f)
                )
            )
//            .background(
//                brush = Brush.linearGradient(
//                    colors = listOf(
//                        Color(0xC84090C5),
//                        Color(0xDDC0A02C)
//                    ),
//                    start = Offset(0f, Float.POSITIVE_INFINITY),
//                    end = Offset(Float.POSITIVE_INFINITY, 0f)
//                )
//            )
    ) {
        RegisterTable(navController)
        Spacer(Modifier.height(70.dp))
        RegistDescript()
        Spacer(Modifier.height(50.dp))
        TextEmail(emailInput)
        Spacer(Modifier.height(10.dp))
        VerifyButton(
            onClick = {
                if (emailInput.isValid) {
                    clickEvent(emailInput.text)
                    GlobalViewModel.updateEmail(emailInput.text)
//                    GlobalViewModel.updateVerifyCode(verifyCode)
                    println("check!2222222")
//                    println(GlobalViewModel.getVerifyCode())
                }
            }
        )
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
        Column() {
            Text(
                "开启我的大学一刻",
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
fun TextEmail(emailInput:EmailState){
//    var text by remember{ mutableStateOf("") }

    val textState = remember {
        EmailState()
    }

    Surface(
        shape = RoundedCornerShape(30.dp),
        color = Color(0x51E4DFDB),
        modifier = Modifier
            .size(width = 700.dp, height = 51.dp)
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
            placeholder = { Text("请输入邮箱号进行注册",
                modifier = Modifier
//                    .padding(start = 75.dp ,end =50.dp )
                    .fillMaxWidth(),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center
                ) },
//            shape = RoundedCornerShape(30.dp)
        )
    }
}

@Composable
fun VerifyButton(
//    navController: NavController,
//                 emailInput: EmailState,
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
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("获取邮箱验证码",
                modifier = Modifier
                .padding(vertical = 15.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                style =MaterialTheme.typography.button,
                color = Color(0xFF0D0D0E),
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }

    }
}