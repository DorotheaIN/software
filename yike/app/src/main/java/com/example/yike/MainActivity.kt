package com.example.yike

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import br.com.onimur.handlepathoz.HandlePathOz
import br.com.onimur.handlepathoz.HandlePathOzListener
import br.com.onimur.handlepathoz.model.PathOz
import com.example.yike.ui.screens.ActivityDetailDisplayScreen
import com.example.yike.ui.screens.ActivityScreen

import com.example.yike.ui.theme.YikeTheme
import com.example.yike.view.*
import com.example.yike.viewModel.*

class MainActivity : ComponentActivity() {
    private lateinit var handlePathOz: HandlePathOz
    private val listener = object: HandlePathOzListener.SingleUri{
        override fun onRequestHandlePathOz(pathOz: PathOz, tr: Throwable?) {
            println("The real path is: ${pathOz.path}")
            val sq = pathOz.path.split("/", ".")
            println(sq)
            when(sq[sq.lastIndex]){
                "jpg" -> GlobalViewModel.updateImg(pathOz.path)
                "png" -> GlobalViewModel.updateImg(pathOz.path)
                "pdf" -> GlobalViewModel.updateDoc(pathOz.path)
                "docx" -> GlobalViewModel.updateDoc(pathOz.path)
                else -> null
            }
            tr?.let {
                println("${it.message}")
            }
        }
    }

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val permission =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
                // true: 用户同意   false：用户不同意 or 用户不处理
                for(i in it){
                    if(i.value) Toast.makeText(this, "SUCCESSFUL", Toast.LENGTH_LONG).show()
                }
            }
        permission.launch(arrayOf(Manifest.permission.MANAGE_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE))
        handlePathOz = HandlePathOz(this, listener)
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
                            }
                        )
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
                        DetailedScreen(navController = navController,detailedAnswerViewModel)
                    }
                    composable(
                        route = "publishAnswer_screen/{questionId}/{questionTitle}",
                        arguments = listOf(
                            navArgument("questionId") { type = NavType.StringType},
                            navArgument("questionTitle") { type = NavType.StringType},
                            )
                    ){
                        val questionId = it.arguments?.getString("questionId")?:""
                        val questionTitle = it.arguments?.getString("questionTitle")?:""
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
                        RegisterOfficialScreen(navController,officialRegisterViewModel,handlePathOz)
                    }
                    composable(
                        route = "personRegister2_screen",
                    ){
                        val getPersonRegisterViewModel = GetPersonRegisterViewModel()
                        RegisterTwoScreen(navController,getPersonRegisterViewModel)
                    }
                }

            }
        }
    }
}

