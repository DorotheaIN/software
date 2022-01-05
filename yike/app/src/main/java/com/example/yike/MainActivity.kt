package com.example.yike

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
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
    @ExperimentalMaterialApi
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
                        LoginScreen(viewModel,
                            {
                                navController.navigate("discuss")
                            },
                            {
                                navController.navigate("orgLogin")
                            },
                            {
                                navController.navigate("personRegister_screen")
                            },
                            {
                                navController.navigate("findBack_screen")
                            },
                            {
                                navController.navigate("adminLogin")
                            }
                        )
                    }
                    composable("orgLogin") {
                        val viewModel = OrgLoginViewModel()
                        OrgLoginScreen(viewModel,
                            {
                                navController.navigate("organization")
                            },
                            {
                                navController.navigate("login")
                            },
                            {
                                navController.navigate("officialRegister_screen")
                            },
                            {
                                navController.navigate("findBack_screen")
                            },
                            {
                                navController.navigate("adminLogin")
                            }
                        )
                    }
//                    composable(
//                        route = "register/{start}",
//                        arguments = listOf(navArgument("start") { type = NavType.StringType })
//                    ) {
//                        val start = it.arguments?.getString("start")?:""
//                        if(start == "personRegister_screen"){
//
//                        }
//                    }
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
                    composable("myactivity"){
                        val infoActivityViewModel = InfoActivityViewModel()
                        InfoActivityScreen(navController,infoActivityViewModel)
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
                    composable(
                        "activityreflect/{id}",
                        arguments = listOf(navArgument("id"){type = NavType.IntType})
                    ){
                            entry->
                        val id = entry.arguments?.getInt("id")
                        if (id != null) {
                            val activityReflectViewModel = ActivityReflectViewModel(id)
                            ActivityReflectScreen(navController = navController,activityReflectViewModel)
                        }
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
                        val reportViewModel = ReportViewModel()
                        DetailedScreen(navController = navController,detailedAnswerViewModel,reportViewModel)
                    }
                    composable(
//                        route = "publishAnswer_screen/{questionId}/{questionTitle}/{answerId}",
                        route = "publishAnswer_screen/{questionId}/{questionTitle}",
                        arguments = listOf(
                            navArgument("questionId") { type = NavType.StringType},
                            navArgument("questionTitle") { type = NavType.StringType},
//                            navArgument("answerId") { type = NavType.StringType}
                            )
                    ){
                        val questionId = it.arguments?.getString("questionId")?:""
                        val questionTitle = it.arguments?.getString("questionTitle")?:""
//                        val answerId = it.arguments?.getString("answerId")?:""
                        val addAnswerViewModel = AddAnswerViewModel(questionId,questionTitle)
                        AnswerScreen(navController = navController,addAnswerViewModel)
                    }
                    composable(
                        route = "inputComment_Screen/{answerId}",
                        arguments = listOf(navArgument("answerId") { type = NavType.StringType})
                    ){
                        val answerId = it.arguments?.getString("answerId")?:""
                        val commentViewModel = CommentViewModel()
                        CommentScreen(navController,commentViewModel,answerId)
                    }
                    composable(
                        route = "publishQuestion_Screen"
                    ){
                        val addQuestionViewModel = AddQuestionViewModel()
                        PublishQuestionScreen(navController,addQuestionViewModel)
                    }
                    composable("personRegister_screen"){
                        val viewModel = SendEmailViewModel()
                        ResgisterScreen(navController, viewModel)
                    }
                    composable("officialRegister_screen"){
                        val officialRegisterViewModel = OfficialRegisterViewModel()
                        RegisterOfficialScreen(navController,officialRegisterViewModel)
                    }
                    composable(
                        route = "personRegister2_screen",
                    ){
                        val getPersonRegisterViewModel = GetPersonRegisterViewModel()
                        val verifyCodeViewModel =VerifyCodeViewModel()
                        RegisterTwoScreen(navController,getPersonRegisterViewModel,verifyCodeViewModel)
                    }
                    composable("findBack_screen"){
                        findBackPasswordScreen(navController)
                    }
                    composable("adminLogin") {
                        val adminLoginViewModel = AdminLoginViewModel()
                        adminLoginScreen(adminLoginViewModel,
                            {
                                navController.navigate("adminApply_screen")
                            },
                            {
                                navController.navigate("login")
                            },
                            {
                                navController.navigate("orgLogin")
                            }
                        )
                    }

                    composable("adminApply_screen"){
                        val adminApplyViewModel =AdminApplyViewModel()
                        val updateApplyViewModel = UpdateApplyViewModel()
                        AdminApplyScreen(adminApplyViewModel,updateApplyViewModel,navController,
                        ) {
                            navController.navigate("adminReport_screen")
                        }
                    }

                    composable("adminReport_screen"){
                        val adminGetReportsViewModel = AdminGetReportsViewModel()
                        val adminUpdateIUserViewModel = AdminUpdateIUserViewModel()
                        AdminReportScreen(adminGetReportsViewModel,adminUpdateIUserViewModel,navController)
                    }
                }

            }
//            MainInfoScreen()
//            RegisterUI()
//            val adminApplyViewModel =AdminApplyViewModel()
//            val updateApplyViewModel = UpdateApplyViewModel()
//            AdminApplyScreen(adminApplyViewModel,updateApplyViewModel)

//            val navController = rememberNavController()
//            NavHost(
//                navController = navController,
//                startDestination = "adminLogin",
//                route = "root"
//            ) {
//                composable("adminLogin") {
//                    val adminLoginViewModel = AdminLoginViewModel()
//                    adminLoginScreen(adminLoginViewModel,
//                        {
//                            navController.navigate("adminApply_screen")
//                        },
//                        {
//                            navController.navigate("login")
//                        },
//                        {
//                            navController.navigate("orgLogin")
//                        }
//                    )
//                }
//
//                composable("adminApply_screen"){
//                    val adminApplyViewModel =AdminApplyViewModel()
//                    val updateApplyViewModel = UpdateApplyViewModel()
//                    AdminApplyScreen(adminApplyViewModel,updateApplyViewModel,navController,
//                    ) {
//                        navController.navigate("adminReport_screen")
//                    }
//                }
//
//                composable("adminReport_screen"){
//                    val adminGetReportsViewModel = AdminGetReportsViewModel()
//                    val adminUpdateIUserViewModel = AdminUpdateIUserViewModel()
//                    AdminReportScreen(adminGetReportsViewModel,adminUpdateIUserViewModel,navController)
//                }
//            }

        }
    }
}