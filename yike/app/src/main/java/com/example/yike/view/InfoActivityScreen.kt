package com.example.yike

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.yike.data.ActivityDetail
import com.example.yike.data.activityDetailList
import com.example.yike.viewModel.Activity
import com.example.yike.viewModel.ActivityViewModel
import com.example.yike.viewModel.InfoActivityViewModel


@Composable
fun InfoActivityScreen(
    navController: NavController,
    viewModel: InfoActivityViewModel
){
    val isGet = viewModel.isGet.observeAsState()
    if(isGet.value != true){
        viewModel.getMyActivities()
    }else {
        val myActivityList = viewModel.myActivities.observeAsState()
        ActivityScreenContent(navController,myActivityList.value)
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
fun ActivityScreenContent(
    navController: NavController,
    activityList: ArrayList<Activity>?
){
    if(activityList == null){
        Scaffold() {
            Loader(paddingValues = it)
        }
    } else{
        LazyColumn(Modifier){
            item {
                ActivityTable(navController)
            }
            item{
                activityList.forEach{
                    ActivityItem(it,navController)
                }
            }
        }
    }

}

@Composable
private fun ActivityTable(
    navController: NavController
){
    Row() {
        IconButton(
            onClick = {
                navController.popBackStack()//回退
            }, //do something
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Icon(
                Icons.Filled.ArrowBack,
                null,
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.CenterVertically),
                tint = Color(0xFFB1A8A1)
            )
        }
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = "我报名的活动",
            color = Color.Black,
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .padding(16.dp, 16.dp, 16.dp, 16.dp)
                .align(Alignment.CenterVertically),
        )
    }
}


@Composable
fun ActivityItem(item: Activity, navController: NavController){
    Surface(
        shape = MaterialTheme.shapes.medium, // 使用 MaterialTheme 自带的形状
        elevation = 5.dp,
        modifier = Modifier
            .padding(0.dp, 7.dp)
            .fillMaxWidth()
    ) {
        Column() {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .size(600.dp, 170.dp)
                    .clickable {
                        navController.navigate("activitydetail/${item.id}")
                    },
                painter = rememberImagePainter(item.img),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Box(
                modifier = Modifier
                    .size(550.dp, 85.dp)
                    .padding(3.dp, 0.dp)
                    .clickable { }
                    .background(Color.White)
            ){
                Row(modifier = Modifier.padding(all = 8.dp)) {
                    Image(
                        painter = rememberImagePainter(item.organizer.avator),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(0.dp, 5.dp)
                            .size(55.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .fillMaxSize()
//                .border(1.5.dp, MaterialTheme.colors.secondary, RoundedCornerShape(7.dp))
                    )
                    Spacer(modifier = Modifier.width(15.dp))

                    Column (modifier = Modifier.size(300.dp,65.dp)){
                        Text(
                            text = item.title,
                            color = Color.Black,
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = item.organizer.username,
                            color = Color(0xFF7A7A7A),
                            style = MaterialTheme.typography.caption
                        )
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Add,null)
                    }
                }
            }
        }
    }


}