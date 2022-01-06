package com.example.yike.view

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import br.com.onimur.handlepathoz.HandlePathOz
import com.example.yike.component.OriganizationTopBar
import com.example.yike.component.*
import com.example.yike.viewModel.Activity
import com.example.yike.viewModel.ActivityPublishViewModel
import com.example.yike.viewModel.GlobalViewModel
import com.example.yike.viewModel.Organization
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ActivityPublishScreen(
    navController: NavController,
    viewModel: ActivityPublishViewModel,
    handlePathOz: HandlePathOz
) {
    viewModel.init()

    val organization = viewModel.GetOrgInfo
    organization?.let {
        ActivityPublishContent(navController, it,handlePathOz){ activity ->
        viewModel.publish(activity)
    }
    }
    val publishRes = viewModel.publishRes.observeAsState()
}

@Composable
fun ActivityPublishContent(
    navController: NavController,
    organization: Organization,
    handlePathOz: HandlePathOz,
    clickEvent: (activity: Activity) ->Unit,
){
    val imgUri = GlobalViewModel.imgUri.observeAsState()
    println("!!!imgURi")
    println(imgUri)
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

        val date = remember { RequiredInputState() }

        val time = remember { RequiredInputState() }
        val timeTemp = remember { RequiredInputState() }
        val place = remember { RequiredInputState() }
        val form = remember { RequiredInputState() }
        val capacity = remember { RequiredInputState() }
        val content = remember { RequiredInputState() }
        val intro = remember { RequiredInputState() }
        val genres = remember { RequiredInputState() }
        val selectedPlace = remember { mutableStateOf("") }
        val selectedTime = remember { mutableStateOf("") }
        val openDialog = remember { mutableStateOf(false) }
        val dialogText = remember { mutableStateOf("") }
        // 形式：长期-日期+日期，短期-日期+时间+时间，线下-地点，线上-平台名+号码
        LazyColumn(Modifier){
            item{
                TitleTextField(title,true)
            }
            item{
                RadioGroupDemo(selectedPlace,selectedTime,date)
            }
            item{
                DatePicker(context,selectedTime.value,date)
            }
            if(selectedTime.value == "短期"){
                item{
                    TimePicker(context,timeTemp)
                }
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
                NumberPickerDemo(context,capacity)
            }
            item{
                IntroTextField(intro,true)
            }
            item{
                ContentTextField(content,true)
            }
            item {
                CheckBoxTest(genres)
            }
            item{
                UpdateImg()
            }
            item{
                Spacer(Modifier.height(15.dp))
                PublishSubmitButton(){
                    dialogText.value = "请完整填写活动详细信息再提交！"
                    if(selectedPlace.value!="" && selectedTime.value!=""){
                        form.text = selectedPlace.value+"-"+selectedTime.value
                    }
                    if(selectedTime.value == "长期"){
                        if(date.text.contains('至')){
                            time.text = date.text
                        }else {
                            dialogText.value = date.text
                            time.text = ""
                        }
                    }else {
                        time.text = date.text + " "+timeTemp.text
                    }
                    if(form.isValid && title.isValid && time.isValid && place.isValid &&
                            capacity.isValid && content.isValid && intro.isValid && genres.isValid && imgUri.value != null){
                        run{
                            val activity = Activity(title.text,
                                "http://101.132.138.14/files/DZY/"+imgUri.value,
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
                    }else {
                        openDialog.value = true
                    }
                }
            }
            item{
                FreeTextDialog(dialogText,openDialog)
            }
            item{
                Spacer(Modifier.height(60.dp))
            }
        }
    }
}

@Composable
fun UpdateImg(){
    val image = remember { mutableStateOf<Uri?>(null) }
    val imgLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        image.value = it
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .height(40.dp),
        ){
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center
            ){
                Text("海报")
            }
        }
        Divider(modifier = Modifier.height(2.dp))
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



