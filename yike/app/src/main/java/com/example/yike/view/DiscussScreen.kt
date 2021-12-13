package com.example.yike.view

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.yike.DiscussTheme
import com.example.yike.component.NavBottomBar
import com.example.yike.component.ThemeCard
import com.example.yike.component.QuestionList
import com.example.yike.defaultDiscussThemes
import com.example.yike.viewModel.DiscussViewModel
import com.example.yike.viewModel.GlobalViewModel
import com.example.yike.viewModel.QTheme
import com.example.yike.viewModel.Question

@Composable
fun DiscussScreen(
    viewModel: DiscussViewModel,
    navController: NavController,
) {
    val isGet = viewModel.isGet.observeAsState()
    if (isGet.value != true) {
        viewModel.getQuestionList()
    } else {
        val questionList = viewModel.questionList.observeAsState()
        val questionTheme = viewModel.questionTheme.observeAsState()
        DiscussScreenScaffold(questionList.value, questionTheme.value,navController)
    }
}

@Composable
private fun DiscussScreenScaffold(
    questionList: ArrayList<Question>?,
    questionTheme: ArrayList<QTheme>?,
    navController: NavController
) {
    Scaffold(
        bottomBar = {
            NavBottomBar(navController, "Discuss")
        }
    ) { paddingValues ->
        if (questionList == null || questionTheme == null) {
            DiscussScreenLoader(paddingValues)
        } else {
            GlobalViewModel.updataQuestionList(questionList, questionTheme)
            DiscussScreenContent(
                paddingValues,
                questionList,
                questionTheme,
                navController,
                { question ->
                    println(question.id)
                    navController.navigate("question/${question.id}") },
                { theme ->
                    navController.navigate("question/${theme.id}")
                }
            )
        }
    }
}

@Composable
private fun DiscussScreenLoader(
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
private fun DiscussScreenContent(
    paddingValues: PaddingValues,
    questionList: ArrayList<Question>,
    questionTheme: ArrayList<QTheme>,
    navController: NavController,//whl增加
    routeEvent1: (q: Question) -> Unit,
    routeEvent2: (q: QTheme) -> Unit

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

            SearchInput()

            DiscussThemesSection(
                questionTheme,
                routeEvent2
            )

            DiscussItemsSection(
                questionList,
                navController,
                routeEvent1,
            )
        }
    }
}

@Composable
private fun DiscussItemsSection(
    items: ArrayList<Question>,
    navController: NavController,//whl增加
    routeEvent: (q: Question) -> Unit
) {

//    val newitems = items.asFlow()
////    items.flatMapConcat { it.asFlow() }
//
//    val lazyPagingItems = newitems.collectAsLazyPagingItems()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Discuss List",
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .paddingFromBaseline(
                    top = 40.dp,
                    bottom = 16.dp,
                )
                .weight(1F),
        )

        Icon(
            Icons.Default.FilterList,
            contentDescription = "Filter",
            modifier = Modifier
                .size(24.dp)
        )
        IconButton(onClick = {
            navController.navigate("publishQuestion_Screen")
        }) {
            Icon(
                Icons.Outlined.Add,
                contentDescription = "Add",
                modifier = Modifier
                    .size(24.dp)
            )
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp),
    ) {
        items.forEach{item ->
            QuestionList(
                item = item,
                onClick = {
                    // 是否要顺便将GlobalVM中存储的回答置空？
                    item?.let(routeEvent)
                })
        }
    }

//    LazyColumn(
//        verticalArrangement = Arrangement.spacedBy(8.dp),
//        modifier = Modifier
//            .padding(horizontal = 16.dp)
//            .padding(bottom = 16.dp),
//    ) {
//        items(items) {item ->
//            ListForItem(item = item)
//        }
//    }
    
//    LazyColumn(
//        verticalArrangement = Arrangement.spacedBy(8.dp),
//        modifier = Modifier
//            .padding(horizontal = 16.dp)
//            .padding(bottom = 16.dp),
//    ) {
//        itemsIndexed(lazyPagingItems) { index, item ->
//            if(item != null) {
//                ListForItem(item = item)
//            } else {
//
//            }
//
//        }
//    }

}

@Composable
private fun DiscussThemesSection(
    themes: ArrayList<QTheme>,
    routeEvent: (q: QTheme) -> Unit
) {
    Text(
        text = "Themes",
        style = MaterialTheme.typography.h6,
        modifier = Modifier
            .paddingFromBaseline(32.dp)
            .padding(horizontal = 16.dp),
    )

    Spacer(Modifier.height(16.dp))

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
    ) {
        themes.forEach { theme ->
            ThemeCard(theme) {
                theme?.let(routeEvent)
            }
        }
    }
}

@Composable
private fun SearchInput() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        label = {
            Text("Search")
        },
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier
                    .size(18.dp)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    )
}

//@Preview(
//    name = "Night Mode",
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//)
//@Preview(
//    name = "Day Mode",
//    uiMode = Configuration.UI_MODE_NIGHT_NO,
//)
//@Composable
//private fun HomeScreenPreview() {
//    // 使用了假数据
//    val previewState = DiscussViewState(
//        discussThemes = defaultDiscussThemes,
//        discussItems = defaultDiscussItems.take(2),
//    )
//
//    YikeTheme {
//        DiscussScreenScaffold(previewState)
//    }
//}