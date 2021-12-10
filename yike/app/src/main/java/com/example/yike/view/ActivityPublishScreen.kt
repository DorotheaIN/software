package com.example.yike.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.yike.component.OriganizationTopBar
import com.example.yike.component.*
import com.example.yike.viewModel.ActivityPublishViewModel

@Composable
fun ActivityPublishScreen(
    navController: NavController,
    activityPublishViewModel: ActivityPublishViewModel
) {
    ActivityPublishContent(navController)
}

@Composable
fun ActivityPublishContent(
    navController: NavController
){
    Scaffold(
        topBar = {
            OriganizationTopBar()
        }
    ) {
        LazyColumn(Modifier){
            item{
                TitleTextField("",true)
            }
            item{
                TimeTextField("",true)
            }
            item{
                PlaceTextField("",true)
            }
            item{
                FormTextField("",true)
            }
            item{
                CapacityTextField("",true)
            }
            item{
                IntroTextField("",true)
            }
            item{
                ContentTextField("",true)
            }
            item {
                GenresTextField("",true)
            }
            item{
                Spacer(Modifier.height(15.dp))
                PublishSubmitButton(navController)
            }
            item{
                Spacer(Modifier.height(60.dp))
            }
        }
    }
}

@Composable
fun PublishSubmitButton(navController: NavController){
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
                fontFamily = FontFamily.SansSerif
            )
        }
    }
}