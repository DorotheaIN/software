package com.example.yike.component

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yike.R
import kotlin.math.roundToInt


@Composable
fun ScrollableAppBar(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.app_name), //默认为应用名
    navigationIcon: @Composable (() -> Unit) =
        { Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "ArrowBack",tint = Color.White) }, //默认为返回键
    @DrawableRes backgroundImageId:Int, // 背景图片
    background: Color = MaterialTheme.colors.primary,
    scrollableAppBarHeight: Dp, //ScrollableAppBar高度
    toolbarOffsetHeightPx:MutableState<Float> //向上偏移量
) {

    // 应用栏最大向上偏移量
    val maxOffsetHeightPx = with(LocalDensity.current) { scrollableAppBarHeight.roundToPx().toFloat() - toolBarHeight.roundToPx().toFloat() }
    // Title 偏移量参考值
    val titleOffsetWidthReferenceValue = with(LocalDensity.current) { navigationIconSize.roundToPx().toFloat() }

    Box(modifier = Modifier
        .height(scrollableAppBarHeight)
        .offset {
            IntOffset(x = 0, y = toolbarOffsetHeightPx.value.roundToInt()) //设置偏移量
        }
        .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .size(600.dp, 170.dp)
                .clickable {
                },
            painter = painterResource(id = backgroundImageId),
            contentDescription = "background",
            contentScale = ContentScale.FillBounds
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFF7F6351), Color(0x807F6351)),
                        start = Offset(0f, Float.POSITIVE_INFINITY),
                        end = Offset(Float.POSITIVE_INFINITY, 0f)
                    )
                )
        ){
            // 自定义应用栏
            Row(
                modifier = modifier
                    .offset {
                        IntOffset(
                            x = 0,
                            y = -toolbarOffsetHeightPx.value.roundToInt() //保证应用栏是始终不动的
                        )
                    }
                    .height(toolBarHeight)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 导航图标
                Box(modifier = Modifier.size(navigationIconSize),contentAlignment = Alignment.Center) {
                    navigationIcon()
                }
            }

            Box(
                modifier = Modifier
                    .height(toolBarHeight) //和ToolBar同高
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .offset {
                        IntOffset(
                            x = -((toolbarOffsetHeightPx.value / maxOffsetHeightPx) * titleOffsetWidthReferenceValue).roundToInt(),
                            y = 0
                        )
                    },
                contentAlignment = Alignment.CenterStart
            ) {
                Text(text = title,color = Color.White,modifier = Modifier.padding(start = 20.dp),fontSize = 20.sp)
            }
        }

    }
}

// 应用栏高度
private val toolBarHeight = 56.dp
// 导航图标大小
private val navigationIconSize = 50.dp

@Preview
@Composable
fun scrollPreview(){
    val scrollableAppBarHeight = 170.dp
    // 应用栏最大向上偏移量
    val maxOffsetHeightPx = with(LocalDensity.current) { scrollableAppBarHeight.roundToPx().toFloat() - toolBarHeight.roundToPx().toFloat() }
    // Title 偏移量参考值
    val titleOffsetWidthReferenceValue = with(LocalDensity.current) { navigationIconSize.roundToPx().toFloat() }
    val toolbarOffsetHeightPx = remember { mutableStateOf(0f) }
    Box(modifier = Modifier
        .height(scrollableAppBarHeight)
        .offset {
            IntOffset(x = 0, y = toolbarOffsetHeightPx.value.roundToInt()) //设置偏移量
        }
        .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .size(600.dp, 170.dp)
                .clickable {
                },
            painter = painterResource(id = R.drawable.jiaxing),
            contentDescription = "background",
            contentScale = ContentScale.FillBounds
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFF7F6351), Color(0x807F6351)),
                        start = Offset(0f, Float.POSITIVE_INFINITY),
                        end = Offset(Float.POSITIVE_INFINITY, 0f)
                    )
                )
        ){
            // 自定义应用栏
            Row(
                modifier = Modifier
                    .offset {
                        IntOffset(
                            x = 0,
                            y = -toolbarOffsetHeightPx.value.roundToInt() //保证应用栏是始终不动的
                        )
                    }
                    .height(toolBarHeight)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 导航图标
                Box(modifier = Modifier.size(navigationIconSize),contentAlignment = Alignment.Center) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "ArrowBack",tint = Color.White)
                }
            }

            Box(
                modifier = Modifier
                    .height(toolBarHeight) //和ToolBar同高
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .offset {
                        IntOffset(
                            x = -((toolbarOffsetHeightPx.value / maxOffsetHeightPx) * titleOffsetWidthReferenceValue).roundToInt(),
                            y = 0
                        )
                    },
                contentAlignment = Alignment.CenterStart
            ) {
                Text(text = "嘉定之星",color = Color.White,modifier = Modifier.padding(start = 20.dp),fontSize = 20.sp)
            }
        }

    }
}