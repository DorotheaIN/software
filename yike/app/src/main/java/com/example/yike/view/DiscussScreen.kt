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
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.yike.DiscussTheme
import com.example.yike.ListForItem
import com.example.yike.ThemeCard
import com.example.yike.defaultDiscussThemes
import com.example.yike.viewModel.DiscussViewModel
import com.example.yike.viewModel.Question

@Composable
fun DiscussScreen(
    navController: NavController
) {
    val viewModel = DiscussViewModel()
    val questionList = viewModel.questionList.observeAsState()
    DiscussScreenScaffold(questionList.value,navController)
}

@Composable
private fun DiscussScreenScaffold(
    questionList: ArrayList<Question>?,
    navController: NavController
) {
    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) { paddingValues ->
        if (questionList == null) {
            DiscussScreenLoader(paddingValues)
        } else {
            DiscussScreenContent(
                paddingValues,
                questionList,
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
private fun BottomBar(navController: NavController) {
    BottomAppBar(
        backgroundColor = MaterialTheme.colors.primary,
    ) {
        BottomButton(
            selected = true,
            icon = Icons.Default.Home,
            labelText = "Home",
            navController
        )
        BottomButton(
            selected = false,
            icon = Icons.Default.FavoriteBorder,
            labelText = "Favorites",
            navController
        )
        BottomButton(
            selected = false,
            icon = Icons.Default.AccountCircle,
            labelText = "Profile",
            navController,
        )
        BottomButton(
            selected = false,
            icon = Icons.Default.ShoppingCart,
            labelText = "Cart",
            navController
        )
    }
}

@Composable
private fun RowScope.BottomButton(
    selected: Boolean,
    icon: ImageVector,
    labelText: String,
    navController: NavController
) {
    BottomNavigationItem(
        selected = selected,
        onClick = {
            //获取点击id
            //存储
            navController.navigate("question")
                  println("click!!!")
        },
        icon = {
            Icon(
                icon,
                contentDescription = null,
            )
        },
        label = {
            Text(labelText)
        }
    )
}

@Composable
private fun DiscussScreenContent(
    paddingValues: PaddingValues,
    questionList: ArrayList<Question>,
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
                defaultDiscussThemes
            )

            DiscussItemsSection(
                questionList
            )
        }
    }
}

@Composable
private fun DiscussItemsSection(
    items: ArrayList<Question>
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

        Icon(
            Icons.Outlined.Add,
            contentDescription = "Add",
            modifier = Modifier
                .size(24.dp)
        )
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp),
    ) {
        items.forEach{item ->
            ListForItem(item = item)
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
    themes: List<DiscussTheme>
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
            ThemeCard(theme)
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