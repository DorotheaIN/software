package com.example.yike.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.yike.component.*
import com.example.yike.viewModel.Activity
import com.example.yike.viewModel.ActivityViewModel


@Composable
fun ActivityScreen(
    navController: NavController,
    viewModel: ActivityViewModel
){
    val isGet = viewModel.isGet.observeAsState()
    if(isGet.value != true){
        viewModel.getActivityList()
    }else {
        val activityList = viewModel.activityList.observeAsState()
        ActivityScreenContent(navController,activityList.value)
    }
}

@Composable
fun ActivityScreenContent(
    navController: NavController,
    activityList:ArrayList<Activity>?
){
    Scaffold(
        modifier = Modifier.background(color = Color(0xffecedef)),
        bottomBar = {
            NavBottomBar(navController,"Activity")
        },
//        modifier = Modifier.background(color = Color(0xffecedef)),
    ) { paddingValues ->
        if( activityList == null){
            filter()
//            Loader(paddingValues)
        }else{
            LazyColumn(Modifier){
                item {
                    filter()
                }
                item(activityList){
                    Column(){
                        activityList.forEach{it->
                            ActivityItem(it){id->
                                navController.navigate("activitydetail/${id}")
                            }
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(170.dp))
                }
            }
        }
    }
}

@Composable
private fun filter(){
    Column(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            Modifier
                .fillMaxWidth(0.9f)
                .align(Alignment.CenterHorizontally)
        ){
            SearchInput()
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            Modifier
                .fillMaxWidth(0.9f)
                .align(Alignment.CenterHorizontally)
        ){
            Row(){
                val tags = listOf<String>("学术","联谊","体育","艺术","党建","环保","庆典","志愿","心理")
                val timeStates = listOf<String>("未开始","进行中","已结束")
                val subStates = listOf<String>("所有","可报名")
                DropDownMenu(tags)
                Spacer(modifier = Modifier.width(15.dp))
                DropDownMenu(timeStates)
                Spacer(modifier = Modifier.width(15.dp))
                DropDownMenu(subStates)
            }
        }
    }
}

@Composable
private fun SearchInput() {
    var text by remember { mutableStateOf("") }
    BasicTextField(
        value = text,
        onValueChange = {
            text = it
        },
        modifier = Modifier
            .border(1.dp,Color(0xFFE5E6E7), CircleShape)
            .height(35.dp)
            .fillMaxWidth(),
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Box(
                    modifier = Modifier.weight(1f).padding(horizontal = 15.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    innerTextField()
                }
                Icon(
                    Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier
                        .size(18.dp)
                )
            }
        }
    )

//    OutlinedTextField(
//        value = "",
//        onValueChange = {},
//        label = {
//            Text("输入活动标题关键词检索")
//        },
//        trailingIcon = {
//            Icon(
//                Icons.Default.Search,
//                contentDescription = null,
//                modifier = Modifier
//                    .size(18.dp)
//            )
//        },
////        leadingIcon = {
////
////        },
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(24.dp)
//            .padding(horizontal = 5.dp),
//    )
}


@Composable
private fun Loader(
    paddingValues: PaddingValues
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center)
        )
    }
}

@Composable
fun ActivityTable(){

}


@Composable
fun ActivityItem(
    item: Activity,
    onClick:(id:Int)->Unit
){
    Surface(
        shape = MaterialTheme.shapes.medium, // 使用 MaterialTheme 自带的形状
        elevation = 5.dp,
        modifier = Modifier
            .padding(15.dp, 11.dp)
            .fillMaxWidth()
    ) {
        Column() {
//            Spacer(modifier = Modifier.width(15.dp))
            Box(
                modifier = Modifier
//                    .size(550.dp, 85.dp)
                    .padding(3.dp, 3.dp, 0.dp, 5.dp)
                    .clickable { }
                    .background(Color.White)
            ){
                Row(modifier = Modifier.padding(horizontal = 8.dp)) {
                    Column(
//                        verticalArrangement = Arrangement.Center,
//                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(2.dp))
                        Image(
                            painter = rememberImagePainter(item.organizer.avator),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(0.dp, 5.dp)
                                .size(35.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .fillMaxSize()
                                .clickable { }
                        )
                    }
                    Spacer(modifier = Modifier.width(15.dp))

                    Column (){
                        Text(
                            text = item.organizer.username,
                            color = Color.Black,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = item.organizer.introduction,
                            color = Color(0xFF7A7A7A),
                            fontSize = 12.sp,
                        )
                    }
                }
            }
//            Spacer(modifier = Modifier.width(5.dp))
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .size(600.dp, 170.dp)
                    .clickable {
                        onClick(item.id)
                    },
                painter = rememberImagePainter(item.img),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
//            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
//                    .size(550.dp, 85.dp)
                    .padding(3.dp, 5.dp, 3.dp, 15.dp)
                    .clickable { }
                    .background(Color.White)
            ){
                Row(modifier = Modifier.padding(horizontal = 8.dp)) {

                    Column (){
                        Text(
                            text = item.title,
                            color = Color.Black,
                            fontSize = 17.sp,
                            modifier = Modifier.padding(0.dp, 5.dp, 0.dp, 0.dp)
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Row(){
                            Text(
                                text = item.date,
                                color = Color(0xFF7A7A7A),
                                fontSize = 13.sp
                            )
                            Spacer(modifier = Modifier.width(15.dp))
                            Text(
                                text = item.place,
                                color = Color(0xFF7A7A7A),
                                fontSize = 13.sp
                            )
                        }

                    }
                }
            }
//            Spacer(modifier = Modifier.width(15.dp))
        }
    }


}



