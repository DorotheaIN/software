package com.example.yike.view

import androidx.compose.foundation.Image
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistryOwner
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import com.example.yike.R
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Face
import androidx.compose.material.icons.sharp.NoteAdd
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import com.example.yike.component.OrgRegisterDialog
import com.example.yike.view.RegisterButton

import br.com.onimur.handlepathoz.HandlePathOz
import coil.compose.rememberImagePainter
import com.example.yike.NameInputState
import com.example.yike.PasswordInputState

import com.example.yike.viewModel.GlobalViewModel
import com.example.yike.viewModel.OfficialRegisterViewModel
import kotlinx.coroutines.currentCoroutineContext


@Composable
fun RegisterOfficialScreen(
    navController: NavController,
    officialRegisterViewModel: OfficialRegisterViewModel,
    handlePathOz: HandlePathOz){
    val path = "http://101.132.138.14/files/DZY/"
    val officialRegister = officialRegisterViewModel.officialRegisterInfo.observeAsState()
    val imgUri = GlobalViewModel.imgUri.observeAsState()
    val docUri = GlobalViewModel.docUri.observeAsState()
    var clickEvent:(s1: String, s2: String, s3: String)-> Unit = {
            _, _, _ -> {}
    }
    if(imgUri.value != null && docUri.value != null) {
        println("changeCE")
        clickEvent= { introduction, password, userName ->
            println("test${path+imgUri}")
            println("test${path+docUri}")
            officialRegisterViewModel.checkOfficialRegisterStatus(path+imgUri.value!!, path+docUri.value!!, introduction, password, userName)
            navController.navigate("login")
        }
    }
    RegisterOfficialScreenContent(navController,officialRegister.value,
    clickEvent = clickEvent,
    updateEvent = { uri ->
        handlePathOz.getRealPath(uri)
    })
}

@Composable
fun RegisterOfficialScreenContent(
    navController: NavController,
    registerResult:String?,
    clickEvent:(introduction:String,password:String,userName:String) ->Unit,
    updateEvent: (uri: Uri) -> Unit = {},
){
    val officialNameInput = remember { NameInputState() }
    val officialCodeInput = remember { PasswordInputState() }
    val officialIntroInput = remember { NameInputState() }
    val isSuccess = remember { mutableStateOf(false)}
    val openDialog = remember { mutableStateOf(false)}
    if(registerResult !=null){
        isSuccess.value = true
        openDialog.value = true
        OrgRegisterDialog(isSuccess = isSuccess, openDialog = openDialog){
            navController.navigate("orgLogin")
        }
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
        Box(
            Modifier.align(Alignment.TopStart)
        ){
            OfficialRegisterTable(navController)
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
                RegistOfficialDescript()
                Spacer(Modifier.height(30.dp))
                OfficialTextName(officialNameInput)
                Spacer(Modifier.height(10.dp))
                OfficialTextCode(officialCodeInput)
                Spacer(Modifier.height(10.dp))
                TextIntro(officialIntroInput)
                Spacer(Modifier.height(10.dp))
                Box(Modifier.align(Alignment.CenterHorizontally)){
                    UploadPicFile(updateEvent)
                }
                Spacer(Modifier.height(10.dp))
                OfficialRegisterButton(
                    onClick = {
                        if(officialNameInput.isValid && officialCodeInput.isValid && officialNameInput.isValid){
                            clickEvent(officialIntroInput.text,officialCodeInput.text,officialNameInput.text)
                        }
                    }
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(0.dp, 30.dp),
            contentAlignment = Alignment.Center
        ){
            TermsOfServiceLabel(){
                navController.navigate("orgLogin")
            }
        }
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

@Composable
private fun TermsOfServiceLabel(
    registerEvent: () -> Unit = {}
) {
    val text = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color(0xFFC7C7C7),
                fontSize = 16.sp
            )
        ){
            append("已有账号？")
        }
        pushStringAnnotation(
            tag = "tag",
            annotation = "转到登录界面"
        )
        withStyle(
            style = SpanStyle(
                color = Color(0xFF227AFF),
                fontSize = 16.sp
            )
        ) {
            append("立刻登录")
        }
        pop()
    }
    ClickableText(
        text = text,
        modifier = Modifier
            .paddingFromBaseline(top = 24.dp),
        onClick = { offset ->
            text.getStringAnnotations(
                tag = "tag", start = offset,
                end = offset
            ).firstOrNull()?.let { annotation ->
                run(registerEvent)
            }
        },
    )
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
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .fillMaxWidth()
//                .padding(start = 100.dp, end = 80.dp)
        )
    }
}

@Composable
fun OfficialTextName(nameInput: NameInputState){
    val textName = remember {
        NameInputState()
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
//                    .padding(start = 115.dp, end = 50.dp)
                    .fillMaxWidth(),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center
            ) },
            shape = RoundedCornerShape(30.dp)
        )
    }
}

@Composable
fun OfficialTextCode(passwordInput: PasswordInputState){
    val textCode = remember {
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
//                    .padding(start = 115.dp, end = 50.dp)
                    .fillMaxWidth(),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center
            ) },
            shape = RoundedCornerShape(30.dp)
        )
    }
}

@Composable
fun TextIntro(officialIntroInput: NameInputState){
    val textIntro = remember {
        NameInputState()
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
//                    .padding(start = 115.dp, end = 50.dp)
                    .fillMaxWidth()
                ,
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center
            ) },
            shape = RoundedCornerShape(30.dp)
        )
    }
}


@Preview
@Composable
fun UploadPicFile(
    updateEvent: (uri: Uri) -> Unit = {},
){
    val image = remember { mutableStateOf<Uri?>(null) }
    val document = remember { mutableStateOf<Uri?>(null) }
    val imgLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        image.value = it
    }
    val documentLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        document.value = it
    }

    Row(
//    modifier = Modifier
//        .fillMaxWidth()
) {
        Box(Modifier.align(Alignment.CenterVertically)){
            IconButton(onClick = {
//            imgLauncher.launch(arrayOf("image/*")) //OpenDocument
                imgLauncher.launch("image/*")
            }) {
                if(image.value != null){
                    Image(
                        rememberImagePainter(image.value.toString()),
                        contentDescription = "official avatar",
                        modifier = Modifier
                            .size(50.dp) // 改变 Image 元素的大小
                            .clip(CircleShape) // 将图片裁剪成圆形
                    )
                    updateEvent(image.value!!)
                }else{
                    Icon(
                        Icons.Sharp.Face,
                        contentDescription = "uploadPic",
                        modifier = Modifier
                            .size(250.dp)
                    )
                }
            }
        }

        Box(modifier = Modifier.width(70.dp))

        Box(Modifier.align(Alignment.CenterVertically)){
            IconButton(onClick = {
                documentLauncher.launch("application/*")
            }) {
                if(document.value != null){
                    Image(
                        painterResource(id = R.drawable.pdf),
                        contentDescription = "pdf icon",
                        modifier = Modifier
                            .size(250.dp) // 改变 Image 元素的大小
                    )
                    updateEvent(document.value!!)
                }else{
                    Icon(
                        Icons.Sharp.NoteAdd,
                        contentDescription = "uploadFile",
                        modifier = Modifier
                            .size(250.dp)
                    )
                }
            }
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
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text("点击注册",
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