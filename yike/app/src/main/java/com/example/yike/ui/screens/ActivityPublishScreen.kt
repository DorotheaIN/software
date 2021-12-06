package com.example.yike.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.yike.data.activityDetailList

@Composable
fun ActivityPublishScreen(navController: NavController) {
    LazyColumn(Modifier){
        item {
            bodyTextField()
        }
    }
}

@Preview
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
            modifier = Modifier.padding(20.dp,5.dp).fillMaxWidth()
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
                modifier = Modifier.padding(20.dp,5.dp).fillMaxWidth(0.45f)
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
                modifier = Modifier.padding(20.dp,5.dp).fillMaxWidth(1f)
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
                modifier = Modifier.padding(20.dp,5.dp).fillMaxWidth(0.45f)
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
                modifier = Modifier.padding(20.dp,5.dp).fillMaxWidth(1f)
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
            modifier = Modifier.padding(20.dp,5.dp).fillMaxWidth()
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