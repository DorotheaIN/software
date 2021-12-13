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
                            val activityDetailViewModel = ActivityDetailViewModel()
                            ActivityDetailDisplayScreen(id,navController = navController,activityDetailViewModel)
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
                    composable("mainInfo_screen"){
                        MainInfo(navController = navController)
                    }
                    composable("infoPublishQuestion_screen")
                    {
                        val publishQuestionViewModel = PublishQuestionViewModel()
                        InfoPublishQuesScreen(navController = navController,publishQuestionViewModel)
                    }
                    composable("infoFollowQuestion_screen")
                    {
                        val followQuestionViewModel = FollowQuestionViewModel()
                        InfoFollowQuesScreen(navController = navController,followQuestionViewModel)
                    }
                    composable(
                        route = "detailed_screen/{questionId}/{answerId}",
                        arguments = listOf(navArgument("questionId") { type = NavType.StringType },navArgument("answerId") { type = NavType.StringType })
                    ){
                        val questionId = it.arguments?.getString("questionId")?:""
                        val answerId = it.arguments?.getString("answerId")?:""
                        val detailedAnswerViewModel = DetailedAnswerViewModel(questionId,answerId)
                        DetailedScreen(navController = navController,detailedAnswerViewModel)
                    }
                    composable(
                        route = "publishAnswer_screen/{questionId}/{questionTitle}/{answerId}",
                        arguments = listOf(
                            navArgument("questionId") { type = NavType.StringType},
                            navArgument("questionTitle") { type = NavType.StringType},
                            navArgument("answerId") { type = NavType.StringType}
                            )
                    ){
                        val questionId = it.arguments?.getString("questionId")?:""
                        val questionTitle = it.arguments?.getString("questionTitle")?:""
                        val answerId = it.arguments?.getString("answerId")?:""
                        val addAnswerViewModel = AddAnswerViewModel(questionId,questionTitle,answerId)
                        AnswerScreen(navController = navController,addAnswerViewModel)
                    }
                    composable(
                        route = "inputComment_Screen/{answerId}",
                        arguments = listOf(navArgument("answerId") { type = NavType.StringType})
                    ){
                        val answerId = it.arguments?.getString("answerId")?:""
                        val commentViewModel = CommentViewModel()
                        CommentScreen(navController = navController,commentViewModel,answerId)
                    }

                }

            }
//            MainInfoScreen()
//            RegisterUI()
        }
    }
}