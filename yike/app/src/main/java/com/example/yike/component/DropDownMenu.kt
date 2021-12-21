package com.example.yike.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DropDownMenu(){
    val cityList = listOf<String>("a","b","c")
    Box {
        // State variables
        var cityName : String by remember{ mutableStateOf(cityList[0]) }
        var expanded by remember { mutableStateOf(false) }

        // Back arrow here
        Row(
            Modifier.clickable{
                expanded = !expanded
            }
        ) { // Anchor view
            Text(text = cityName,) // City name label
            Icon(imageVector = Icons.Filled.ArrowDropDown,"")
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }) {
                cityList.forEach { city ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        cityName = city
                    }) {
                        val isSelected = city == cityName
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
                        Text(text= city, style = style)
                    }
                }
            }
        }
    }
}