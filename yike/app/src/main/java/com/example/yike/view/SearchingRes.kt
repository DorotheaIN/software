package com.example.yike.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.yike.component.DiscussTopBar
import com.example.yike.viewModel.*

@Composable
fun SearchingResScreen(
    viewModel: SearchViewModel,
    navController: NavController,
) {
    val questionList = viewModel.getQuestionList()
    SeachScreenScaffold(questionList, navController)
//    val isGet = viewModel.isGet.observeAsState()
//    if (isGet.value != true) {
//        viewModel.getQuestionListByKyWd()
//    } else {
//        val questionList = viewModel.questionList.observeAsState()
//        SeachScreenScaffold(questionList.value, navController)
//    }
}

@Composable
private fun SeachScreenScaffold(
    questionList: ArrayList<Question>?,
    navController: NavController
) {
    Scaffold(
        topBar = {
            DiscussTopBar { navController.navigate("discuss") }
        }
    ) { paddingValues ->
        if (questionList == null) {
            SearchScreenLoader(paddingValues)
        } else {
            SearchScreenContent(
                paddingValues,
                questionList,
            ) { question ->
                println(question.id)
                navController.navigate("question/${question.id}")
            }
        }
    }
}

@Composable
private fun SearchScreenContent(
    paddingValues: PaddingValues,
    questionList: ArrayList<Question>,
    routeEvent: (q: Question) -> Unit,
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {
            Spacer(Modifier.height(40.dp))
            SearchingContent(
                questionList,
                routeEvent,
            )
        }
    }
}

@Composable
private fun SearchScreenLoader(
    paddingValues: PaddingValues
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center)
        )
    }
}

@Composable
private fun SearchingContent(
    items: ArrayList<Question>,
    routeEvent: (q: Question) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp),
    ) {
        items.forEach{item ->
            com.example.yike.component.QuestionList(
                item = item,
                onClick = {
                    item?.let(routeEvent)
                })
        }
    }
}