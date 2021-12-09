package com.example.yike

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.yike.ui.theme.YikeTheme
import com.example.yike.view.DiscussScreen
import com.example.yike.view.LoginScreen
import com.example.yike.view.QuestionScreen
import com.example.yike.view.WelcomeScreen
import com.example.yike.viewModel.DiscussViewModel
import com.example.yike.viewModel.LoginViewModel
import com.example.yike.viewModel.QuestionViewModel

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
                        DiscussScreen(viewModel){ question ->
                            navController.navigate("question/${question.id}")
                        }
                    }
                    composable(
                        route = "question/{questionId}",
                        arguments = listOf(navArgument("questionId") { type = NavType.StringType })
                    ) {
                        val questionId = it.arguments?.getString("questionId")?:""
                        val questionViewModel = QuestionViewModel(questionId)
                        QuestionScreen(questionViewModel)
                    }
//                    composable(
//                        route = "question") {
//                        QuestionScreen()
//                    }
                }

            }
        }
    }
}