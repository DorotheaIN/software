package com.example.yike

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.yike.data.ActivityDetail
import com.example.yike.data.activityDetailList
import com.example.yike.ui.screens.*
import com.example.yike.ui.theme.YikeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YikeUI()
        }
    }
}



@Composable
fun YikeUI(){
    YikeTheme() {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "home_screen" ,
        ){
            composable("home_screen"){
                HomeScreen(navController = navController)
            }
            composable("share_screen"){

            }
            composable("question_screen"){
                OrganizationScreen()
            }
            composable("activity_screen"){
                ActivityScreen(navController = navController)
            }
            composable("person_screen"){
                ActivityPublishScreen(navController = navController)
            }
            composable(
                "activitydetail_screen/{id}",
                arguments = listOf(navArgument("id"){type = NavType.IntType})
            ){
                    entry->
                val id = entry.arguments?.getInt("id")
                if (id != null) {
                    ActivityDetailDisplayScreen(id,navController = navController)
                }
            }
        }
    }
}

