package com.example.yike.component

import android.content.Context
import androidx.annotation.ArrayRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.max

/**
 * 自定义下拉列表
 * @author markrenChina
 *
 * @param modifier 约束
 * @param onSpinnerItemSelected item点击事件
 * @param itemList 显示下拉的字符串列表（可修改为泛型）
 * @param position 当前选择的位置
 * @param title 下拉标题
 * @param itemListRes R.array 优先于itemList
 */
@Composable
fun Spinner(
    modifier: Modifier,
    onSpinnerItemSelected: (Int) -> Unit = { _ -> },
    itemList: List<String> = listOf(),
    @ArrayRes itemListRes: Int? = null,
    position: Int? = null,
    title: String? = null,
    context: Context
) {
    // 是否展开
    var expanded by remember{ mutableStateOf(false) }

    SpinnerSpacer(visible = expanded)
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        elevation = 2.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .animateContentSize()
        ) {
            //1.
            var items = itemList
            itemListRes?.let {
                items = context.resources.getStringArray(it).toList()
            }
            var showItem = if (expanded) {
                title ?: position?.let { items[it] } ?: "Spinner"
            } else {
                position?.let { items[it] } ?: title ?: "Spinner"
            }
            //2.
            AroundRow {
                Text(
                    text = showItem,
                    style = MaterialTheme.typography.h6
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                )
            }
            //3.
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                repeat(items.size) { index ->
                    DropdownMenuItem(onClick = {
                        //子传父 index
                        onSpinnerItemSelected(index)
                        expanded = false
                    }) {
                        Text(text = items[index])
                    }
                }
            }

        }

    }
    SpinnerSpacer(visible = expanded)
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun SpinnerSpacer(visible: Boolean) {
    AnimatedVisibility(visible = visible) {
        Spacer(modifier = Modifier.height(8.dp))
    }
}




