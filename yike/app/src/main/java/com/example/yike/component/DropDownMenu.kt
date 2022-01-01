package com.example.yike.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DropDownMenu(
    tags: List<String>,
    type:String,
    event: (value:String)->Unit,
){
    Box(
    ) {
        // State variables
        var tagName : String by remember{ mutableStateOf(tags[0]) }
        var expanded by remember { mutableStateOf(false) }

        // Back arrow here
        Row(
            Modifier.clickable{
                expanded = !expanded
            }
        ) { // Anchor view
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }) {
                tags.forEach { item ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        tagName = item
                        run{
                            if(tagName=="所有"){
                                event("")
                            }else{
                                event(tagName)
                            }
                        }
                    }) {
                        val isSelected = item == tagName
                        val style = if (isSelected) {
                            MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.secondary
                            )
                        } else {
                            MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colors.onSurface
                            )
                        }
                        Text(text= item, style = style)
                    }
                }
            }
            Box(
                Modifier.border(1.dp, Color(0xFFF2F2F2), RoundedCornerShape(10.dp)).size(width=112.dp,height = 36.dp)
            ){
                Row(
                    Modifier.fillMaxHeight().padding(vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = tagName,) // City name label
                    Spacer(modifier = Modifier.width(15.dp))
                    Icon(imageVector = Icons.Filled.ArrowDropDown,"")
                }
            }
        }
    }
}