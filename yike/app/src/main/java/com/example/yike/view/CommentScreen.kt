package com.example.yike.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.yike.NameInputState
import com.example.yike.viewModel.CommentViewModel
import com.example.yike.viewModel.GlobalViewModel
import org.w3c.dom.Comment

@Composable
fun CommentScreen(
    navController: NavController,
    commentViewModel: CommentViewModel,
    answerId:String
    ){

    val inputComment = commentViewModel.inputComment.observeAsState()

    CommentScreenContent(navController,answerId){
        answerId, content, userId ->  commentViewModel.checkInputComment(answerId,content, userId)
    }
}


@Composable
fun CommentScreenContent(
    navController: NavController,
    answerId: String,
    clickEvent:(answerId: String,content:String,userId: String) -> Unit,
){
    val commentInput = remember { NameInputState() }
    Scaffold(
        Modifier.padding(0.dp),
        topBar = {
            TopAppBar(
                title = {
                    Button(onClick = {
                        navController.popBackStack()//回退
                    },
                        colors = ButtonDefaults.buttonColors(
                            Color(0xFFFFFF),
                            Color(0xFFFFFF)
                        ),
                        elevation = ButtonDefaults.elevation(0.dp,0.dp,0.dp)
                    ){
                        Text("取消",
                            color = Color(0xFFB1A8A1)
                        )
                    }
                },
                backgroundColor = Color(0xFFFFFF),
                contentColor = Color(0xFFFFFF),
                elevation = 0.dp,
                actions = {
                    Button(
                        onClick = {
                                  if(commentInput.isValid){
                                      GlobalViewModel.getUserInfo()?.let {
                                          clickEvent(answerId,commentInput.text,
                                              it.id)
                                      }
                                      navController.popBackStack()//回退
                                  }
                        },
                        colors = ButtonDefaults.buttonColors(
                            Color(0xFFFFFF),
                            Color(0xFFFFFF)
                        ),
                        elevation = ButtonDefaults.elevation(0.dp,0.dp,0.dp)
                    ){
                        Text("评论",
                            color = Color(0xFF1084E0)
                        )
                    }
                }
            )
        },
    ){
        Surface(
            color = Color(0x51E4DFDB),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TextField(
                value = commentInput.text,
                onValueChange = {
                    commentInput.text = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color(0xFF0D0D0E),
                    backgroundColor = Color.Transparent,
                    cursorColor = Color(0xFF045DA0),
                ),
                placeholder = { Text("快来评论吧",
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color(0xFFBBB4B4)
                ) },
                shape = RoundedCornerShape(30.dp)
            )
        }
    }
}