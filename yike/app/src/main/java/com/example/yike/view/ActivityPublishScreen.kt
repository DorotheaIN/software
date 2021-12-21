package com.example.yike.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.yike.component.OriganizationTopBar
import com.example.yike.component.*
import com.example.yike.viewModel.Activity
import com.example.yike.viewModel.ActivityPublishViewModel
import com.example.yike.viewModel.Organization

@Composable
fun ActivityPublishScreen(
    navController: NavController,
    viewModel: ActivityPublishViewModel
) {
    viewModel.init()
    val organization = viewModel.GetOrgInfo
    organization?.let {
        ActivityPublishContent(navController, it){ activity ->
        viewModel.publish(activity)
    }
    }
    val publishRes = viewModel.publishRes.observeAsState()
}

@Composable
fun ActivityPublishContent(
    navController: NavController,
    organization: Organization,
    clickEvent: (activity: Activity) ->Unit
){
    val context = LocalContext.current
    Scaffold(
        topBar = {
            OriganizationTopBar(organization.username){
                navController.navigate("organization")
            }
        },
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        //val activity = remember { mutableStateOf(Activity("","","","","","","","",0,0,1,0, organization,0)) }
        val title = remember { RequiredInputState()}
        val time = remember { RequiredInputState() }
        val place = remember { RequiredInputState() }
        val form = remember { RequiredInputState() }
        val capacity = remember { RequiredInputState() }
        val content = remember { RequiredInputState() }
        val intro = remember { RequiredInputState() }
        val genres = remember { RequiredInputState() }

        // 形式：长期-日期+日期，短期-日期+时间+时间，线下-地点，线上-平台名+号码
        LazyColumn(Modifier){
            item{
                TitleTextField(title,true)
            }
            item{
                RadioGroupDemo()
            }
            item{
                DatePicker(context = context,"Long")
            }
            item{
                TimePicker(context = context, type = "Short",time)
            }
//            item{
//                TimeTextField(time,true)
//            }
            item{
                PlaceTextField(place,true)
            }
//            item{
//                FormTextField(form,true)
//            }
//            item{
//                CapacityTextField(capacity,true)
//            }
            item { 
                NumberPickerDemo(context = context,capacity)
            }
            item{
                IntroTextField(intro,true)
            }
            item{
                ContentTextField(content,true)
            }
            item {
                CheckBoxTest()
            }
            item{
                Spacer(Modifier.height(15.dp))
                PublishSubmitButton(){
                    if(title.isValid && time.isValid && place.isValid && form.isValid &&
                            capacity.isValid && content.isValid && intro.isValid && genres.isValid){
                        run{
                            val activity = Activity(title.text,
                                "http://mmbiz.qpic.cn/mmbiz_jpg/D9E2nL2KO2kgKU4ibqPWbIUQDP89y7AXicXwc6QiaPTVuRibyB0CPichvm38w3lc1vS3y2DsUiaZFUrhDic92I0H8psTQ/0?wx_fmt=jpeg",
                                time.text,
                                place.text,
                                form.text,
                                intro.text,
                                content.text,
                                genres.text,
                                0,
                                capacity.text.toInt(),
                                1,
                                0,
                                organization,
                                0
                            )
                            clickEvent(activity)
                            navController.navigate("organization")
                        }
                    }
                }
            }
            item{
                Spacer(Modifier.height(60.dp))
            }
        }
    }
}

@Composable
fun PublishSubmitButton(
    onClick: () ->Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xD83D59FC)
            ),
            shape = RoundedCornerShape(35),
            onClick = onClick,
            modifier = Modifier
                .padding(horizontal = 26.dp),
        ) {
            Text(
                text = "提交",
                color = Color.White,
                style = TextStyle(
                    fontSize = 15.sp,
                    letterSpacing = 20.sp
                ),
            )
        }
    }
}



