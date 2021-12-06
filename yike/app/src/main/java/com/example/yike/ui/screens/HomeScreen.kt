package com.example.yike.ui.screens

import androidx.annotation.StringRes
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.composable
import com.example.yike.R

sealed class Screen(val route: String, @StringRes val resourceId: Int,val name: String) {
    object Share : Screen("share_screen", R.string.app_name,"分享")
    object Talk : Screen("question_screen", R.string.bottom_sheet_behavior,"讨论")
    object Activity: Screen("activity_screen", R.string.app_name,"活动")
    object PersonInfo: Screen("person_screen", R.string.app_name,"主页")
}


@Composable
fun HomeScreen(navController: NavController){
    var selectedItem by remember {
        mutableStateOf(2)
    }
    val items = listOf(Screen.Share,Screen.Talk,Screen.Activity,Screen.PersonInfo)
    Scaffold(
        bottomBar = {
            BottomNavigation {
                items.forEachIndexed { index, item ->
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                        label = { Text(item.name) },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            navController.navigate(item.route)
                        }
                    )
                }
            }
        },
        content = {
            ActivityScreen(navController)
        }
    )
}