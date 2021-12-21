package com.example.yike.component

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chillibits.composenumberpicker.PickerButton
import com.example.yike.R

@Composable
fun NumberPickerDemo(context: Context,initial: RequiredInputState){
    var number = remember { mutableStateOf(20) }
    var max = remember { mutableStateOf(40) }
    var min = remember { mutableStateOf(10) }
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
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center
            ){
                Text("容量")
            }
            Spacer(modifier = Modifier.size(5.dp))
            HorizontalNumberPicker(
                min = 10,
                max = 100,
                default = 50,
                onValueChange = { value->
                    Toast.makeText(context,value.toString(),Toast.LENGTH_SHORT).show()
                },
                initial = initial
            )
        }
    }

}

@Composable
fun HorizontalNumberPicker(
    height: Dp = 12.dp,
    min: Int = 0,
    max: Int = 10,
    default: Int = min,
    onValueChange: (Int) -> Unit = {},
    initial: RequiredInputState
) {
    val number = remember { mutableStateOf(default) }
    initial.text = number.value.toString()
    Row(
        Modifier.fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PickerButton(
            size = height,
            drawable = R.drawable.ic_arrow_left,
            enabled = number.value > min,
            onClick = {
                if (number.value > min) number.value--
                onValueChange(number.value)
            }
        )
        Text(
            text = number.value.toString(),
//            fontSize = (height.value / 1).sp,
            modifier = Modifier.padding(10.dp)
                .height(IntrinsicSize.Max)
                .align(Alignment.CenterVertically)
        )
        PickerButton(
            size = height,
            drawable = R.drawable.ic_arrow_right,
            enabled = number.value < max,
            onClick = {
                if (number.value < max) number.value++
                onValueChange(number.value)
            }
        )
    }
}