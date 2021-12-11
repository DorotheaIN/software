package com.example.yike.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.yike.viewModel.Activity

@Composable
fun TitleTextField(initial: RequiredInputState, enabled:Boolean){
    val requiredState = remember{
        RequiredInputState()
    }
//    mutablemap
    if(!requiredState.hasEverBeenFocused){
        requiredState.text = initial.text
    }
    var enable by remember{ mutableStateOf(enabled) }
    var edit by remember{ mutableStateOf(false) }
    TextField(
        value = requiredState.text,
        onValueChange = { newText->
            requiredState.text = newText
            initial.text = requiredState.text
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
fun TimeTextField(initial: RequiredInputState,enabled:Boolean){
    val requiredState = remember{
        RequiredInputState()
    }
    if(!requiredState.hasEverBeenFocused){
        requiredState.text = initial.text
    }
    var enable by remember{ mutableStateOf(enabled) }
    var edit by remember{ mutableStateOf(false) }
    TextField(
        value = requiredState.text,
        onValueChange = { newText->
            requiredState.text = newText
            initial.text = requiredState.text
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
fun PlaceTextField(initial: RequiredInputState,enabled:Boolean){
    val requiredState = remember{
        RequiredInputState()
    }
    if(!requiredState.hasEverBeenFocused){
        requiredState.text = initial.text
    }
    var enable by remember{ mutableStateOf(enabled) }
    var edit by remember{ mutableStateOf(false) }
    TextField(
        value = requiredState.text,
        onValueChange = { newText->
            requiredState.text = newText
            initial.text = requiredState.text
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
fun FormTextField(initial: RequiredInputState,enabled:Boolean){
    val requiredState = remember{
        RequiredInputState()
    }
    if(!requiredState.hasEverBeenFocused){
        requiredState.text = initial.text
    }
    var enable by remember{ mutableStateOf(enabled) }
    var edit by remember{ mutableStateOf(false) }
    TextField(
        value = requiredState.text,
        onValueChange = { newText->
            requiredState.text = newText
            initial.text = requiredState.text
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
fun CapacityTextField(initial: RequiredInputState,enabled:Boolean){
    val requiredState = remember{
        RequiredInputState()
    }
    if(!requiredState.hasEverBeenFocused){
        requiredState.text = initial.text
    }
    var enable by remember{ mutableStateOf(enabled) }
    var edit by remember{ mutableStateOf(false) }
    TextField(
        value = requiredState.text,
        onValueChange = { newText->
            requiredState.text = newText
            initial.text = requiredState.text
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
fun ContentTextField(initial: RequiredInputState,enabled:Boolean){
    val requiredState = remember{
        RequiredInputState()
    }
    if(!requiredState.hasEverBeenFocused){
        requiredState.text = initial.text
    }
    var enable by remember{ mutableStateOf(enabled) }
    var edit by remember{ mutableStateOf(false) }
    TextField(
        value = requiredState.text,
        onValueChange = { newText->
            requiredState.text = newText
            initial.text = requiredState.text
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
fun IntroTextField(initial: RequiredInputState,enabled:Boolean){
    val requiredState = remember{
        RequiredInputState()
    }
    if(!requiredState.hasEverBeenFocused){
        requiredState.text = initial.text
    }
    var enable by remember{ mutableStateOf(enabled) }
    var edit by remember{ mutableStateOf(false) }
    TextField(
        value = requiredState.text,
        onValueChange = { newText->
            requiredState.text = newText
            initial.text = requiredState.text
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
fun GenresTextField(initial: RequiredInputState,enabled:Boolean){
    val requiredState = remember{
        RequiredInputState()
    }
    if(!requiredState.hasEverBeenFocused){
        requiredState.text = initial.text
    }
    var enable by remember{ mutableStateOf(enabled) }
    var edit by remember{ mutableStateOf(false) }
    TextField(
        value = requiredState.text,
        onValueChange = { newText->
            requiredState.text = newText
            initial.text = requiredState.text
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

