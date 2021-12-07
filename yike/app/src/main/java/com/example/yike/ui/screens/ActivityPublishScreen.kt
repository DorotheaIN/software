package com.example.yike.ui.screens

import android.widget.Space
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.yike.component.RequiredInputState
import com.example.yike.component.YikeSecondaryButton
import com.example.yike.data.activityDetailList

@Composable
fun ActivityPublishScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBar()
        }
    ) {
        LazyColumn(Modifier){
            item{
                TitleTextField()
            }
            item{
                TimeTextField()
            }
            item{
                PlaceTextField()
            }
            item{
                FormTextField()
            }
            item{
                CapacityTextField()
            }
            item{
                IntroTextField()
            }
            item{
                ContentTextField()
            }
            item {
                GenresTextField()
            }
            item{
                Spacer(Modifier.height(15.dp))
                SubmitButton(navController)
            }
            item{
                Spacer(Modifier.height(60.dp))
            }
        }
    }

}

@Composable
private fun TopBar() {
    //toolbar重构
    //topappbar
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
    ) {
        IconButton(onClick = {
//            navController.navigate("discuss")
        }) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .size(24.dp)
            )
        }
//
//        IconButton(onClick = {
////            navController.navigate("discuss")
//        }) {
//            Icon(
//                Icons.Default.Search,
//                contentDescription = null,
//                modifier = Modifier
//                    .size(18.dp)
//            )
//        }
    }
}

@Composable
fun SubmitButton(navController: NavController){
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8598FF)),
            shape = RoundedCornerShape(50),
            onClick = { navController.navigate("home_screen") },
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .height(48.dp)
                .padding(horizontal = 16.dp),
        ) {
            Text(
                text = "提交",
                color = Color.White,
                style = MaterialTheme.typography.h5,
                fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif
            )
        }
    }

}

@Composable
fun TitleTextField(){
    val requiredState = remember{
        RequiredInputState()
    }
    var enable by remember{ mutableStateOf(true)}
    var edit by remember{ mutableStateOf(true)}
    TextField(
        value = requiredState.text,
        onValueChange = { newText->
            requiredState.text = newText
        },
        singleLine = false,
        maxLines = 2,
        leadingIcon = {
            Text(text = "标题")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 5.dp)
            .onFocusChanged {
                val isFocused = it.isFocused
                requiredState.onFocusChange(isFocused)
                if (!isFocused) {
                    requiredState.enableShowErrors()
                    if (requiredState.isValid && requiredState.hasEverBeenFocused && !edit) {
                        enable = false
                    }
                    if (edit) {
                        edit = !edit
                    }
                }
            },
        isError = requiredState.showErrors,
        enabled = enable,
        trailingIcon = {
            IconButton(
                onClick = {
                    enable = true
                    edit = true
                },
            ) {
                Icon(Icons.Filled.Edit, null)
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        ),

    )
}

@Composable
fun TimeTextField(){
    val requiredState = remember{
        RequiredInputState()
    }
    var enable by remember{ mutableStateOf(true)}
    var edit by remember{ mutableStateOf(true)}
    TextField(
        value = requiredState.text,
        onValueChange = { newText->
            requiredState.text = newText
        },
        singleLine = true,
        leadingIcon = {
            Text("时间")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 5.dp)
            .onFocusChanged {
                val isFocused = it.isFocused
                requiredState.onFocusChange(isFocused)
                if (!isFocused) {
                    requiredState.enableShowErrors()
                    if (requiredState.isValid && requiredState.hasEverBeenFocused && !edit) {
                        enable = false
                    }
                    if (edit) {
                        edit = !edit
                    }
                }
            },
        isError = requiredState.showErrors,
        enabled = enable,
        trailingIcon = {
            IconButton(
                onClick = {
                    enable = true
                    edit = true
                },
            ) {
                Icon(Icons.Filled.Edit, null)
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        ),
    )
}

@Composable
fun PlaceTextField(){
    val requiredState = remember{
        RequiredInputState()
    }
    var enable by remember{ mutableStateOf(true)}
    var edit by remember{ mutableStateOf(true)}
    TextField(
        value = requiredState.text,
        onValueChange = { newText->
            requiredState.text = newText
        },
        singleLine = true,
        leadingIcon = {
            Text("地点")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 5.dp)
            .onFocusChanged {
                val isFocused = it.isFocused
                requiredState.onFocusChange(isFocused)
                if (!isFocused) {
                    requiredState.enableShowErrors()
                    if (requiredState.isValid && requiredState.hasEverBeenFocused && !edit) {
                        enable = false
                    }
                    if (edit) {
                        edit = !edit
                    }
                }
            },
        isError = requiredState.showErrors,
        enabled = enable,
        trailingIcon = {
            IconButton(
                onClick = {
                    enable = true
                    edit = true
                },
            ) {
                Icon(Icons.Filled.Edit, null)
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        ),
    )
}

@Composable
fun FormTextField(){
    val requiredState = remember{
        RequiredInputState()
    }
    var enable by remember{ mutableStateOf(true)}
    var edit by remember{ mutableStateOf(true)}
    TextField(
        value = requiredState.text,
        onValueChange = { newText->
            requiredState.text = newText
        },
        singleLine = true,
        leadingIcon = {
            Text("形式")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 5.dp)
            .onFocusChanged {
                val isFocused = it.isFocused
                requiredState.onFocusChange(isFocused)
                if (!isFocused) {
                    requiredState.enableShowErrors()
                    if (requiredState.isValid && requiredState.hasEverBeenFocused && !edit) {
                        enable = false
                    }
                    if (edit) {
                        edit = !edit
                    }
                }
            },
        isError = requiredState.showErrors,
        enabled = enable,
        trailingIcon = {
            IconButton(
                onClick = {
                    enable = true
                    edit = true
                },
            ) {
                Icon(Icons.Filled.Edit, null)
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        ),
    )
}

@Composable
fun CapacityTextField(){
    val requiredState = remember{
        RequiredInputState()
    }
    var enable by remember{ mutableStateOf(true)}
    var edit by remember{ mutableStateOf(true)}
    TextField(
        value = requiredState.text,
        onValueChange = { newText->
            requiredState.text = newText
        },
        singleLine = true,
        leadingIcon = {
            Text("人数")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 5.dp)
            .onFocusChanged {
                val isFocused = it.isFocused
                requiredState.onFocusChange(isFocused)
                if (!isFocused) {
                    requiredState.enableShowErrors()
                    if (requiredState.isValid && requiredState.hasEverBeenFocused && !edit) {
                        enable = false
                    }
                    if (edit) {
                        edit = !edit
                    }
                }
            },
        isError = requiredState.showErrors,
        enabled = enable,
        trailingIcon = {
            IconButton(
                onClick = {
                    enable = true
                    edit = true
                },
            ) {
                Icon(Icons.Filled.Edit, null)
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        ),
    )
}

@Composable
fun ContentTextField(){
    val requiredState = remember{
        RequiredInputState()
    }
    var enable by remember{ mutableStateOf(true)}
    var edit by remember{ mutableStateOf(true)}
    TextField(
        value = requiredState.text,
        onValueChange = { newText->
            requiredState.text = newText
        },
        singleLine = false,
        maxLines = 5,
        leadingIcon = {
            Text("内容")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 5.dp)
            .onFocusChanged {
                val isFocused = it.isFocused
                requiredState.onFocusChange(isFocused)
                if (!isFocused) {
                    requiredState.enableShowErrors()
                    if (requiredState.isValid && requiredState.hasEverBeenFocused && !edit) {
                        enable = false
                    }
                    if (edit) {
                        edit = !edit
                    }
                }
            },
        isError = requiredState.showErrors,
        enabled = enable,
        trailingIcon = {
            IconButton(
                onClick = {
                    enable = true
                    edit = true
                },
            ) {
                Icon(Icons.Filled.Edit, null)
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        ),
    )
}

@Composable
fun IntroTextField(){
    val requiredState = remember{
        RequiredInputState()
    }
    var enable by remember{ mutableStateOf(true)}
    var edit by remember{ mutableStateOf(true)}
    TextField(
        value = requiredState.text,
        onValueChange = { newText->
            requiredState.text = newText
        },
        singleLine = false,
        maxLines = 5,
        leadingIcon = {
            Text("介绍")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 5.dp)
            .onFocusChanged {
                val isFocused = it.isFocused
                requiredState.onFocusChange(isFocused)
                if (!isFocused) {
                    requiredState.enableShowErrors()
                    if (requiredState.isValid && requiredState.hasEverBeenFocused && !edit) {
                        enable = false
                    }
                    if (edit) {
                        edit = !edit
                    }
                }
            },
        isError = requiredState.showErrors,
        enabled = enable,
        trailingIcon = {
            IconButton(
                onClick = {
                    enable = true
                    edit = true
                },
            ) {
                Icon(Icons.Filled.Edit, null)
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        ),
    )
}

@Composable
fun GenresTextField(){
    val requiredState = remember{
        RequiredInputState()
    }
    var enable by remember{ mutableStateOf(true)}
    var edit by remember{ mutableStateOf(true)}
    TextField(
        value = requiredState.text,
        onValueChange = { newText->
            requiredState.text = newText
        },
        singleLine = true,
        leadingIcon = {
            Text("类型")
        },
        label = {
            Text("请自定义三个;隔开")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 5.dp)
            .onFocusChanged {
                val isFocused = it.isFocused
                requiredState.onFocusChange(isFocused)
                if (!isFocused) {
                    requiredState.enableShowErrors()
                    if (requiredState.isValid && requiredState.hasEverBeenFocused && !edit) {
                        enable = false
                    }
                    if (edit) {
                        edit = !edit
                    }
                }
            },
        isError = requiredState.showErrors,
        enabled = enable,
        trailingIcon = {
            IconButton(
                onClick = {
                    enable = true
                    edit = true
                },
            ) {
                Icon(Icons.Filled.Edit, null)
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        ),
    )
}


// 弃
@Composable
fun bodyTextField(){
    var title by remember{ mutableStateOf("")}
    var time by remember{ mutableStateOf("")}
    var place by remember{ mutableStateOf("")}
    var form by remember{ mutableStateOf("")}
    var capacity by remember{ mutableStateOf("")}
    var intro by remember{ mutableStateOf("")}
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = title,
            onValueChange = {
                title = it
            },
            singleLine = true,
            leadingIcon = {
                Text("标题")
            },
            trailingIcon = {
                IconButton(
                    onClick = { },
                ) {
                    Icon(Icons.Filled.Edit, null)
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color(0xFF0079D3),
                backgroundColor = Color.Transparent
            ),
            modifier = Modifier
                .padding(20.dp, 5.dp)
                .fillMaxWidth()
        )
        Row(
            modifier = Modifier.fillMaxWidth(1f)
        ){
            TextField(
                value = time,
                onValueChange = {
                    time = it
                },
                singleLine = true,
                leadingIcon = {
                    Text("时间")
                },
                trailingIcon = {
                    IconButton(
                        onClick = { },
                    ) {
                        Icon(Icons.Filled.Edit, null)
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color(0xFF0079D3),
                    backgroundColor = Color.Transparent
                ),
                modifier = Modifier
                    .padding(20.dp, 5.dp)
                    .fillMaxWidth(0.45f)
            )
            TextField(
                value = place,
                onValueChange = {
                    place = it
                },
                singleLine = true,
                leadingIcon = {
                    Text("地点")
                },
                trailingIcon = {
                    IconButton(
                        onClick = { },
                    ) {
                        Icon(Icons.Filled.Edit, null)
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color(0xFF0079D3),
                    backgroundColor = Color.Transparent
                ),
                modifier = Modifier
                    .padding(20.dp, 5.dp)
                    .fillMaxWidth(1f)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(1f)
        ){
            TextField(
                value = form,
                onValueChange = {
                    form = it
                },
                singleLine = true,
                leadingIcon = {
                    Text("形式")
                },
                trailingIcon = {
                    IconButton(
                        onClick = { },
                    ) {
                        Icon(Icons.Filled.Edit, null)
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color(0xFF0079D3),
                    backgroundColor = Color.Transparent
                ),
                modifier = Modifier
                    .padding(20.dp, 5.dp)
                    .fillMaxWidth(0.45f)
            )
            TextField(
                value = capacity,
                onValueChange = {
                    capacity = it
                },
                singleLine = true,
                leadingIcon = {
                    Text("人数")
                },
                trailingIcon = {
                    IconButton(
                        onClick = { },
                    ) {
                        Icon(Icons.Filled.Edit, null)
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color(0xFF0079D3),
                    backgroundColor = Color.Transparent
                ),
                modifier = Modifier
                    .padding(20.dp, 5.dp)
                    .fillMaxWidth(1f)
            )
        }
        TextField(
            value = intro,
            onValueChange = {
                intro = it
            },
            singleLine = true,
            leadingIcon = {
                Text("简要介绍")
            },
            trailingIcon = {
                IconButton(
                    onClick = { },
                ) {
                    Icon(Icons.Filled.Edit, null)
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color(0xFF0079D3),
                backgroundColor = Color.Transparent
            ),
            modifier = Modifier
                .padding(20.dp, 5.dp)
                .fillMaxWidth()
        )
    }
}



//      BasicTextField(
//            value = text,
//            onValueChange ={
//                text = it
//            },
//            singleLine = true,
//            modifier = Modifier
//                .background(Color.White, CircleShape)
//                .height(35.dp)
//                .fillMaxWidth(),
//            decorationBox = { innerTextField ->
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    modifier = Modifier.padding(horizontal = 10.dp)
//                ) {
//                    Text("标题")
//                    Box(
//                        modifier = Modifier.weight(1f),
//                        contentAlignment = Alignment.CenterStart
//                    ) {
//                        innerTextField()
//                    }
//                    IconButton(
//                        onClick = { },
//                    ) {
//                        Icon(Icons.Filled.Edit, null)
//                    }
//                }
//
//            }
//        )