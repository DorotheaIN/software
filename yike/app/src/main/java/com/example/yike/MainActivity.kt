package com.example.yike

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.yike.ui.theme.YikeTheme
import com.example.yike.view.DiscussScreen
import com.example.yike.viewModel.LoginViewModel
import com.example.yike.viewModel.UserInfo

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
                        LoginScreen { navController.navigate("discuss") }
                    }
                    composable("discuss") {
                        DiscussScreen(navController)
                    }
//                    composable(
//                        route = "question/{questionId}",
//                        arguments = listOf(navArgument("questionId") { type = NavType.IntType })) {
//                            val questionId = it.arguments?.getString("questionId")?:""
//                            QuestionScreen()
//                    }
                    composable(
                        route = "question") {
                        QuestionScreen()
                    }
                }

            }
        }
    }
}