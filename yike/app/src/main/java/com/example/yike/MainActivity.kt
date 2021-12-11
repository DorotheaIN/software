package com.example.yike

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.yike.ui.screens.ActivityDetailDisplayScreen
import com.example.yike.ui.screens.ActivityScreen

import com.example.yike.ui.theme.YikeTheme
import com.example.yike.view.*
import com.example.yike.viewModel.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YikeTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "login",
                    route = "root"
                ) {
                    composable("welcome") {
                        WelcomeScreen(navController)
                    }
                    composable("login") {
                        val viewModel = LoginViewModel()
                        LoginScreen(viewModel) {
                            navController.navigate("discuss")
                        }
                    }
                    composable("discuss") {
                        val viewModel = DiscussViewModel()
                        DiscussScreen(viewModel, navController)
                    }
                    composable(
                        route = "question/{questionId}",
                        arguments = listOf(navArgument("questionId") { type = NavType.StringType })
                    ) {
                        val questionId = it.arguments?.getString("questionId")?:""
                        val questionViewModel = QuestionViewModel(questionId)
                        QuestionScreen(questionViewModel, navController)
                    }
                    composable("activity"){
                        val activityViewModel = ActivityViewModel()
                        ActivityScreen(navController = navController,activityViewModel)
                    }
                    composable("organization"){
                        val organizationViewModel = OrganizationViewModel()
                        OrganizationScreen(navController = navController,organizationViewModel)
                    }
                    composable(
                        "activitydetail/{id}",
                        arguments = listOf(navArgument("id"){type = NavType.IntType})
                    ){
                            entry->
                        val id = entry.arguments?.getInt("id")
                        if (id != null) {
                            val activityDetailViewModel = ActivityDetailViewModel(id)
                            ActivityDetailDisplayScreen(navController = navController,activityDetailViewModel)
                        }
                    }
                    composable(
                        "activityedit/{id}",
                        arguments = listOf(navArgument("id"){type = NavType.IntType})
                    ){
                            entry->
                        val id = entry.arguments?.getInt("id")
                        if (id != null) {
                            val activityCorrectViewModel = ActivityCorrectViewModel()
                            ActivityCorrectScreen(id,navController = navController,activityCorrectViewModel)
                        }
                    }
                    composable("activity_publish"){
                        val activityPublishViewModel = ActivityPublishViewModel()
                        ActivityPublishScreen(navController = navController,activityPublishViewModel)
                    }
                }

            }
        }
    }
}