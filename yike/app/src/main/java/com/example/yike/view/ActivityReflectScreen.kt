package com.example.yike.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.yike.component.NavBottomBar
import com.example.yike.viewModel.ActivityReflectViewModel
import com.example.yike.viewModel.EvaluationAnalysis
import com.example.yike.viewModel.UserInfo

@Composable
fun ActivityReflectScreen(
    navController: NavController,
    viewModel:ActivityReflectViewModel
){
    val isGet = viewModel.isGet.observeAsState()
    if( isGet.value != true ){
        viewModel.getAll()
    }else {
        val evaluationAnalysis = viewModel.evaluationAnalysis.observeAsState()
        val subscriberList = viewModel.subscriberList.observeAsState()
        ActivityReflectContent(evaluationAnalysis.value, subscriberList.value)
    }
}

@Composable
fun ActivityReflectContent(
    anaylsis:EvaluationAnalysis?,
    subscriberList:ArrayList<UserInfo>?
){
    Scaffold(){ paddingValues ->
        if(anaylsis == null || subscriberList == null){
            Loader(paddingValues)
        }else {
            LazyColumn() {
                item{
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .size(600.dp, 170.dp)
                            .clickable {
                            },
                        painter = rememberImagePainter(anaylsis.cloud),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth
                    )
                }
                item{
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .size(600.dp, 170.dp)
                            .clickable {
                            },
                        painter = rememberImagePainter(anaylsis.emo_ANALYSIS),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth
                    )
                }
                item{
                    LazyRow(){
                        subscriberList.forEach{ it->
                            item {
                                Column() {
                                    Row(modifier = Modifier.padding(all = 8.dp)) {
                                        Image(
                                            painter = rememberImagePainter(it.avator),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .padding(0.dp, 5.dp)
                                                .size(55.dp)
                                                .clip(RoundedCornerShape(10.dp))
                                                .fillMaxSize()
//                .border(1.5.dp, MaterialTheme.colors.secondary, RoundedCornerShape(7.dp))
                                        )
                                        Spacer(modifier = Modifier.width(15.dp))

                                        Column (modifier = Modifier.size(300.dp,65.dp)){
                                            Text(
                                                text = it.user_NAME,
                                                color = Color.Black,
                                                style = MaterialTheme.typography.h6,
                                                modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                                            )

                                            Spacer(modifier = Modifier.height(4.dp))

                                            Text(
                                                text = it.introduction,
                                                color = Color(0xFF7A7A7A),
                                                style = MaterialTheme.typography.caption
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(15.dp))
                                        IconButton(onClick = { /*TODO*/ }) {
                                            Icon(Icons.Filled.Add,null)
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
    }

}

@Composable
private fun Loader(
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