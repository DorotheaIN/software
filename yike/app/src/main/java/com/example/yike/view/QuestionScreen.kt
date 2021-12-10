package com.example.yike.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.yike.component.AnswerCard
import com.example.yike.component.DiscussTopBar
import com.example.yike.viewModel.Answer
import com.example.yike.viewModel.Question
import com.example.yike.viewModel.QuestionViewModel

@Composable
fun QuestionScreen(
    viewModel: QuestionViewModel,
    navController: NavController,
    ) {
    val isGet = viewModel.isGet.observeAsState()
    if (isGet.value != true) {
        viewModel.getAnswerList()
    } else {
        if (viewModel.isOriginReady()) viewModel.setQuestionStatus() //或者判断是否为-1
        val answerList = viewModel.answerList.observeAsState()
        val questionStatus = viewModel.questionStatus.observeAsState()
//        if(questionStatus.value == -1 )viewModel.setQuestionStatus()
        QuestionScreenScaffold(answerList.value, viewModel.getQuestionBody(),
            questionStatus.value,
            backEvent = {
                viewModel.postQuestionStatus()
                navController.popBackStack()
            },
            clickEvent1 = {
                viewModel.convertQuestionStatus()
        })
    }
}

@Composable
private fun QuestionScreenScaffold(
    answerList: ArrayList<Answer>?,
    questionBody: Question?,
    questionStatus: Int?,
    backEvent: () -> Unit,
    clickEvent1: () -> Unit
) {
    Scaffold(
        topBar = {
            DiscussTopBar(backEvent)
        }
    ) { paddingValues ->
        println(questionStatus)
        if (answerList == null || questionBody == null || questionStatus == null) {
            QuestionScreenLoader(paddingValues)
        } else {
            QuestionScreenContent(
                answerList,
                questionBody,
                questionStatus,
                paddingValues,
                clickEvent1
//                routeEvent

            )
        }
    }
}

@Composable
private fun QuestionScreenLoader(
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
private fun QuestionScreenContent(
    answerList: ArrayList<Answer>,
    questionBody: Question,
    questionStatus: Int,
    paddingValues: PaddingValues,
    clickEvent1: () -> Unit = {}
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
                questionBody
            )

            UserOperationSection(
                questionStatus,
                clickEvent1,
            )

            AnswerListSection(
                answerList
            )
        }
    }
}

@Composable
private fun QuestionSection(
    questionBody: Question
) {
    Surface(
        shape = MaterialTheme.shapes.medium, // 使用 MaterialTheme 自带的形状
        elevation = 5.dp,
        modifier = Modifier.padding(all = 8.dp)
    ) {
        Column{
            Text(
                text = questionBody.title,
                style = MaterialTheme.typography.h3
            )

            Text(//折叠的
                text = questionBody.description,
                style = MaterialTheme.typography.h6
            )
        }
    }
}

@Composable
private fun UserOperationSection(
    questionStatus: Int,
    clickEvent1: () -> Unit = {}
) {
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
                onClick = clickEvent1,
                modifier = Modifier.weight(1f)
            ) {
                Column() {
                    Icon(
                        Icons.Outlined.Star,//交互
                        contentDescription = "Star",
                        tint = if (questionStatus == 1) Color.Yellow else Color.Red
                    )
                    Text("关注")
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
    answerList: ArrayList<Answer>
) {
//    LazyColumn {
//        items(answerModel) { answer ->
//            AnswerCard(answerInfo = answer)
//        }
//    }
    Column() {
        answerList.forEach{answer ->
            AnswerCard(answerInfo = answer)
        }
    }
}

//@Preview(
//    name = "Day Mode",
//    uiMode = Configuration.UI_MODE_NIGHT_NO,
//)
//@Composable
//private fun QuestionScreenPreview() {
//    YikeTheme {
//        QuestionScreen()
//    }
//}