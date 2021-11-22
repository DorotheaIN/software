package com.example.yike

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.yike.ui.theme.YikeTheme

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
                    composable("question") {
                        QuestionScreen()
                    }
                    composable("welcome") {
                        WelcomeScreen(navController)
                    }
                    composable("login") {
                        LoginScreen(navController)
                    }
                    composable("discuss") {
//                        val homeViewModel: HomeViewModel = hiltNavGraphViewModel()
//                        val uiState by homeViewModel.uiState.collectAsState()
//                        val plantList = homeViewModel.pagedPlants
//
//                        HomeScreen(uiState = uiState) { plant ->
//                            navController.navigate("plant/${plant.id}")
//                        }
                        DiscussScreen(navController)
                    }
//                    composable(
//                        route = "plant/{id}",
//                        arguments = listOf(navArgument("id") { type = NavType.IntType })
//                    ) {
//                        val plantViewModel: QuestionViewModel = hiltNavGraphViewModel()
//                        val plant: Plant by plantViewModel.plantDetails.collectAsState(Plant(0))
//
//                        QuestionScreen(plant = plant)
//                    }
                }
            }
        }
    }
}