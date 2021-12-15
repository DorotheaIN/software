package com.example.yike

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.sharp.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.yike.component.NavBottomBar
import com.example.yike.data.Taylor
import com.example.yike.ui.screens.ActivityDetailDisplayScreen
import com.example.yike.view.InfoFollowQuesScreen
import com.example.yike.viewModel.FollowQuestionViewModel
import com.example.yike.viewModel.GlobalViewModel
import com.example.yike.viewModel.PublishQuestionViewModel

@Composable
fun MainInfo(navController: NavController){
    Scaffold(bottomBar = {
        NavBottomBar(navController, "Info")
    }) {
        Surface(
            modifier = Modifier
                .padding(0.dp, 0.dp)
                .fillMaxSize()
                .background(Color(0xffededed)),
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                UserInfo(navController)
                Spacer(Modifier.height(20.dp))
                ToActivity(navController)
                Spacer(Modifier.height(40.dp))
                ToQuestion(navController)
                Spacer(Modifier.height(40.dp))
                ToCollect(navController)
                Spacer(Modifier.height(60.dp))
            }
        }
    }

}

@Composable
fun UserInfo(
    navController: NavController
){
    Surface(
        modifier = Modifier
            .padding(bottom = 7.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium, // 使用 MaterialTheme 自带的形状
        elevation = 5.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xB2806FA0), Color(0xE14256C4)),
                        start = Offset(0f, Float.POSITIVE_INFINITY),
                        end = Offset(Float.POSITIVE_INFINITY, 0f)
                    )
                )
        ) {
            LoginOutItem(navController)
            Spacer(Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .size(550.dp, 85.dp)
                    .padding(15.dp, 0.dp)
                    .clickable { }
            ){
                Row(modifier = Modifier.padding(all = 8.dp)) {
                    Image(
                        painter = rememberImagePainter(GlobalViewModel.getUserInfo()?.avator),
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
                            text = GlobalViewModel.getUserInfo()?.user_NAME.toString(),
                            color = Color.White,
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = GlobalViewModel.getUserInfo()?.introduction.toString(),
                            color = Color(0x7EFFFFFF),
                            style = MaterialTheme.typography.caption
                        )
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Add,null)
                    }
                }
            }
            Divider(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(start = 24.dp, end = 24.dp),
                //颜色
                color = Color(0x2CFFFFFF),
            )
            Spacer(Modifier.height(60.dp))
        }
    }
}

@Composable
private fun LoginOutItem(navController: NavController){
    Box(
        Modifier
            .fillMaxWidth()
            .clickable { }){
        Box(modifier = Modifier
            .align(Alignment.TopEnd)){
            IconButton(onClick = { navController.navigate("login") }) {
                Icon(
                    painter = painterResource(id = R.drawable.loginout),
                    contentDescription = "login out",
                    Modifier.size(24.dp),
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun ToActivity(navController: NavController){
        Surface(
            modifier = Modifier
                .padding(bottom = 5.dp)
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.medium, // 使用 MaterialTheme 自带的形状
            elevation = 10.dp,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
//                    .background(
//                        brush = Brush.linearGradient(
//                            colors = listOf(Color(0x77A6B4FD), Color(0xFFFFFFFF)),
//                            start = Offset(0f, Float.POSITIVE_INFINITY),
//                            end = Offset(Float.POSITIVE_INFINITY, 0f)
//                        )
//                    )
            ) {
                Icon(
                    painterResource(id = R.drawable.activity_pic),
                    contentDescription = "Comment",
                    modifier = Modifier
//                   .size(200.dp)
//                   .padding(10.dp, 10.dp),
                        .padding(0.dp, 5.dp)
                        .size(80.dp)
                        .fillMaxSize(),
                    tint = Color(0xFF6679E4)
                )
                Spacer(modifier = Modifier.width(15.dp))
                TextButton(onClick = {
                    navController.navigate("myactivity")
                }) {
                    Text(
                        text = "我报名的活动",
                        color = Color(0xFF615D5D),
                        style = MaterialTheme.typography.h4,
                        maxLines = 1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                    )
                }
            }
        }
}

@Composable
fun ToQuestion(navController: NavController){
    Surface(
        modifier = Modifier
            .padding(bottom = 5.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium, // 使用 MaterialTheme 自带的形状
        elevation = 10.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
//                .background(
//                    brush = Brush.linearGradient(
//                        colors = listOf(Color(0xFF4090C5), Color(0xFFC0A02C)),
//                        start = Offset(0f, Float.POSITIVE_INFINITY),
//                        end = Offset(Float.POSITIVE_INFINITY, 0f)
//                    )
//                )
        ) {
            Icon(
                painterResource(id = R.drawable.sharp_live_help_black_36),
                contentDescription = "publishQues",
                modifier = Modifier
//                   .size(200.dp)
//                   .padding(10.dp, 10.dp),
                    .padding(0.dp, 5.dp)
                    .size(80.dp)
                    .fillMaxSize(),
                tint = Color(0xEDE95009)
            )
            Spacer(modifier = Modifier.width(15.dp))
            TextButton(onClick = {
//                navController.navigate("infoPublishQuestion_screen")
                navController.navigate("infoPublishQuestion_screen")
            }) {
                Text(
                    text = "我发布的问题",
                    color = Color(0xFF615D5D),
                    style = MaterialTheme.typography.h4,
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                )
            }
        }
    }
}

@Composable
fun ToCollect(navController: NavController){
    Surface(
        modifier = Modifier
            .padding(bottom = 5.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium, // 使用 MaterialTheme 自带的形状
        elevation = 10.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
//                .background(
//                    brush = Brush.linearGradient(
//                        colors = listOf(Color(0xFF4090C5), Color(0xFFC0A02C)),
//                        start = Offset(0f, Float.POSITIVE_INFINITY),
//                        end = Offset(Float.POSITIVE_INFINITY, 0f)
//                    )
//                )
        ) {
            Icon(
                Icons.Sharp.Star,
//              painterResource(id = R.drawable.sharp_live_help_black_36),
                contentDescription = "publishQues",
                modifier = Modifier
//                   .size(200.dp)
//                   .padding(10.dp, 10.dp),
                    .padding(0.dp, 5.dp)
                    .size(80.dp)
                    .fillMaxSize(),
                tint = Color(0xFFEECD6B)
            )
            Spacer(modifier = Modifier.width(15.dp))
            TextButton(onClick = {
                navController.navigate("infoFollowQuestion_screen")
            }) {
                Text(
                    text = "我收藏的问题",
                    color = Color(0xFF615D5D),
                    style = MaterialTheme.typography.h4,
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                )
            }
        }
    }
}

