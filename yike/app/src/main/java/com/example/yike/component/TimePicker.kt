package com.example.yike.component

import android.app.TimePickerDialog
import android.content.Context
import android.widget.TimePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.HOUR_OF_DAY
import java.util.Calendar.MINUTE


@Composable
fun TimePicker(context: Context,type: String,initial: RequiredInputState){
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
                Text("时间")
            }
            Spacer(modifier = Modifier.size(5.dp))
            TimePickerDemo(context,initial)
            if(type == "Short"){
                Row(
                    modifier = Modifier.fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = "   -   ")
                    Spacer(modifier = Modifier.size(8.dp))
                    TimePickerDemo(context,initial)
                }
            }
        }
        Divider(modifier = Modifier.height(2.dp))
    }
}

@Composable
fun TimePickerDemo(context: Context,initial: RequiredInputState){
    val now = Calendar.getInstance()
    val time = remember { mutableStateOf("") }
    initial.text = time.value
    val mHour = now.get(Calendar.HOUR_OF_DAY)
    val mMinute = now.get(Calendar.MINUTE)
    val timePickerDialog = TimePickerDialog(
        context,
        { _: TimePicker, hour: Int, minute: Int ->
            val cal = Calendar.getInstance()
            cal.set(HOUR_OF_DAY,hour)
            cal.set(MINUTE,minute)
            time.value = getFormattedTime(cal.time, "HH:mm")
        },
        mHour,mMinute,true
    )
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ){
        Text(text =
        if(time.value != ""){
            "${time.value}"
        }else{
            "${getFormattedTime(now.time,"HH:mm")}"
        },
            modifier = Modifier.clickable {
                timePickerDialog.show()
            }
        )
    }
    Spacer(modifier = Modifier.size(8.dp))
}

fun getFormattedTime(time: Date?, format: String) : String {
    try {
        if (time != null) {
            val formatter = SimpleDateFormat(format, Locale.getDefault())
            return formatter.format(time)
        }
    } catch (e: Exception) {

    }
    return ""
}