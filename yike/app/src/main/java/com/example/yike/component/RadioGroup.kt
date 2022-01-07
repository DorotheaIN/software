package com.example.yike.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun RadioGroupDemo(
    selectedPlace:MutableState<String>,
    selectedTime:MutableState<String>,
    initial:RequiredInputState
){
    val tagsPlace = arrayListOf("线下","线上")
    val tagsTime = arrayListOf("短期","长期")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
        ) {
            Column(){
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text("形式")
                }
            }
            Column(){
                Row(
                    modifier = Modifier.padding(10.dp,10.dp)
                ){
                    tagsPlace.forEach{
                        Row() {
                            RadioButton(
                                selected = it == selectedPlace.value,
                                onClick = {
                                    selectedPlace.value = it
                                }
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(it)
                            Spacer(modifier = Modifier.width(35.dp))
                        }
                    }
                }
                Row(
                    modifier = Modifier.padding(10.dp,10.dp)
                ){
                    tagsTime.forEach {
                        Row {
                            RadioButton(
                                selected = it == selectedTime.value,
                                onClick = {
                                    if(selectedTime.value != it){
                                        initial.text = ""
                                    }
                                    selectedTime.value = it
                                }
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(it)
                            Spacer(modifier = Modifier.width(35.dp))
                        }
                    }
                }
            }
        }
    }

}



@Composable
fun CheckBoxTest(
    genres:RequiredInputState
){
    val tags = arrayListOf("学术","联谊","体育","艺术","党建","环保","庆典","志愿","心理")
    val values = arrayListOf<MutableState<Boolean>>()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
        ) {
            Column() {
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text("类型")
                }
            }
            Column(){
                Row(modifier = Modifier.padding(10.dp,10.dp)) {
                    for(index in 0..2){
                        val value = remember {
                            mutableStateOf(false)
                        }
                        values.add(value)
                        CheckBoxItem(value, tags[index],genres)
                    }
                }
                Row(modifier = Modifier.padding(10.dp,10.dp)) {
                    for(index in 3..5){
                        val value = remember {
                            mutableStateOf(false)
                        }
                        values.add(value)
                        CheckBoxItem(value, tags[index],genres)
                    }
                }
                Row(modifier = Modifier.padding(10.dp,10.dp)) {
                    for(index in 6..8){
                        val value = remember {
                            mutableStateOf(false)
                        }
                        values.add(value)
                        CheckBoxItem(value, tags[index],genres)
                    }
                }
            }
        }
    }

}

@Composable
fun CheckBoxItem(value: MutableState<Boolean>,name: String,genres:RequiredInputState) {
    val context = LocalContext.current
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val pressState = interactionSource.collectIsPressedAsState()
    val borderColor = if (pressState.value) Color.Green else Color.Black
    Row(){
        Checkbox(
            checked = value.value,
            onCheckedChange = {
                value.value = it
                if(value.value){
                    if(genres.isValid){
                        genres.text=genres.text+","+name
                    }else {
                        genres.text=name
                    }
                }else{
                    println(name)
                    println(genres.text)
                    genres.text = genres.text.replace(name+",","")
                    genres.text = genres.text.replace(","+name,"")
                    genres.text = genres.text.replace(name,"")
                    println(genres.text)
                }
            },
//                    modifier = Modifier.size(50.dp),
            enabled = true,
            interactionSource = interactionSource,
//                colors = CheckboxDefaults.colors(
//                    checkedColor= Color.Red,
//                    uncheckedColor = Color.Gray,
//                    disabledColor = Color.Gray,
//                    checkmarkColor = Color.White,
//                    disabledIndeterminateColor = Color.Yellow
//                )
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(name)
        Spacer(modifier = Modifier.width(15.dp))
    }
}






















