package com.example.yike

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.yike.ui.theme.YikeTheme

@Composable
fun QuestionScreen(

) {
    val answer = AnswerData.answers
    Scaffold(
        topBar = {
            TopBar()
        }
    ) {
        QuestionScreenContent(answer)
    }
}

@Composable
private fun TopBar() {
    //toolbar重构
    //topappbar
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
    ) {
        IconButton(onClick = {
//            navController.navigate("discuss")
        }) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .size(24.dp)
            )
        }

        IconButton(onClick = {
//            navController.navigate("discuss")
        }) {
            Icon(
                Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier
                    .size(18.dp)
            )
        }

    }
}

@Composable
private fun QuestionScreenContent(
    question: List<Answer>
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            QuestionSection(
            )

            UserOperationSection(
            )

            AnswerListSection(
                question
            )
        }
    }
}

@Composable
private fun QuestionSection() {
    Surface(
        shape = MaterialTheme.shapes.medium, // 使用 MaterialTheme 自带的形状
        elevation = 5.dp,
        modifier = Modifier.padding(all = 8.dp)
    ) {
        Column{
            Text(
                "A very very very very very very very very very very very very very very Long Title",
                style = MaterialTheme.typography.h3
            )

            Text(//折叠的
                "Content",
                style = MaterialTheme.typography.h6
            )
        }
    }
}

@Composable
private fun UserOperationSection() {
    Surface(
        shape = MaterialTheme.shapes.medium, // 使用 MaterialTheme 自带的形状
        elevation = 5.dp,
        modifier = Modifier.padding(all = 8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ){
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.weight(1f)
            ) {
                Column() {
                    Icon(
                        Icons.Outlined.Star,//交互
                        contentDescription = "Star"
                    )
                    Text("收藏")
                }
            }

            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.weight(1f)
            ) {
                Column() {
                    Icon(
                        Icons.Outlined.Edit,//交互
                        contentDescription = "Edit"
                    )
                    Text("写回答")
                }
            }
        }
    }

}

@Composable
private fun AnswerListSection(
    answerModel : List<Answer>
) {
//    LazyColumn {
//        items(answerModel) { answer ->
//            AnswerCard(answerInfo = answer)
//        }
//    }
    Column() {
        answerModel.forEach{answer ->
            AnswerCard(answerInfo = answer)
        }
    }
}

@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun QuestionScreenPreview() {
    YikeTheme {
        QuestionScreen()
    }
}