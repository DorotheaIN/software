package com.example.yike.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
            Loader(paddingValues)
        }else{
            LazyColumn(Modifier){
                item {
                    ActivityTable()
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
    Column {
//        Text(
//            text = "活动速览",
//            color = Color.Black,
//            fontSize = 20.sp,
////        style = MaterialTheme.typography.h5,
//            modifier = Modifier.padding(18.dp,16.dp,16.dp,16.dp)
//        )
        DropDownMenu()
//        val context = LocalContext.current
//        var operatorPos by remember { mutableStateOf(1) }
//        val operator = remember { listOf("admin", "小明", "小王") }
//        Spinner(
//            modifier = Modifier
//                .weight(1f)
//                .padding(2.dp),
//            onSpinnerItemSelected = { operatorPos = it },
//            position = operatorPos,
//            itemList = operator,
//            title = "检测人员",
//            context = context
//        )

//        DropDownList(
//            false,
//            listOf("x","y","z"),
//        )
//        CountrySelection()
    }


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



