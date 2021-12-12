package com.example.yike.view

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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.yike.component.*
import com.example.yike.viewModel.Activity
import com.example.yike.viewModel.ActivityCorrectViewModel
import com.example.yike.viewModel.ActivityDetail
import com.example.yike.viewModel.Organization

@Composable
fun ActivityCorrectScreen(
    id:Int,
    navController: NavController,
    viewModel: ActivityCorrectViewModel
) {
    viewModel.init(id)
    val activityDetail = viewModel.activityInfo.observeAsState()
    val organization = viewModel.GetOrgInfo
    organization?.let {
        activityDetail.value?.let { it1 ->
            ActivityCorrectContent(navController, it, it1){ activity->
            viewModel.correct(activity)
            navController.navigate("organization")
        }
        }
    }
    val correctRes = viewModel.correctRes.observeAsState()
}

@Composable
fun ActivityCorrectContent(
    navController: NavController,
    organization: Organization,
    activityDetail:ActivityDetail,
    clickEvent: (activity: Activity) ->Unit
){
    Scaffold(
        topBar = {
            OriganizationTopBar(){
                navController.navigate("organization")
            }
        }
    ) {
        val title = remember { RequiredInputState()}
        val time = remember { RequiredInputState() }
        val place = remember { RequiredInputState() }
        val form = remember { RequiredInputState() }
        val capacity = remember { RequiredInputState() }
        val content = remember { RequiredInputState() }
        val intro = remember { RequiredInputState() }
        val genres = remember { RequiredInputState() }
        LazyColumn(Modifier){
            item{
                title.text = activityDetail.title
                TitleTextField(title,false)
            }
            item{
                time.text = activityDetail.date
                TimeTextField(time,false)
            }
            item{
                place.text = activityDetail.place
                PlaceTextField(place,false)
            }
            item{
                form.text = activityDetail.form
                FormTextField(form,false)
            }
            item{
                capacity.text = activityDetail.capacity.toString()
                CapacityTextField(capacity,false)
            }
            item{
                intro.text = activityDetail.introduction
                IntroTextField(intro,false)
            }
            item{
                content.text = activityDetail.content
                ContentTextField(content,false)
            }
            item {
                genres.text = activityDetail.genres
                GenresTextField(genres,false)
            }
            item{
                Spacer(Modifier.height(15.dp))
                CorrectSubmitButton(){
                    if(title.isValid && time.isValid && place.isValid && form.isValid &&
                        capacity.isValid && content.isValid && intro.isValid && genres.isValid){
                        run{
                            val activity = Activity(title.text,
                                "http://mmbiz.qpic.cn/mmbiz_jpg/D9E2nL2KO2kgKU4ibqPWbIUQDP89y7AXicXwc6QiaPTVuRibyB0CPichvm38w3lc1vS3y2DsUiaZFUrhDic92I0H8psTQ/0?wx_fmt=jpeg",
                                time.text,
                                place.text,
                                form.text,
                                intro.text,
                                content.text,
                                genres.text,
                                0,
                                capacity.text.toInt(),
                                1,
                                0,
                                organization,
                                activityDetail.id
                            )
                            clickEvent(activity)
                        }
                    }
                }
            }
            item{
                Spacer(Modifier.height(60.dp))
            }
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        backgroundColor = Color(0xffdaa545),
    ) {
        IconButton(onClick = {
//            navController.navigate("discuss")
        }) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .size(24.dp),
                tint = Color.White
            )
        }
    }
}

@Composable
fun CorrectSubmitButton(
    onClick: () ->Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffdaa545)),
            shape = RoundedCornerShape(50),
            onClick = onClick,
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