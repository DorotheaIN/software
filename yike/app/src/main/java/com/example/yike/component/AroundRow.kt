package com.example.yike.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.max

/**
 * 自定义左右置顶布局
 * @author markrenChina
 * @param startPadding 左边留空
 * @param endPadding 右边留空
 */
@Composable
fun AroundRow(
    modifier: Modifier = Modifier,
    startPadding: Dp = 0.dp,
    endPadding: Dp = 0.dp,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        content = content
    ){ measurables, constraints ->
        val placeables = measurables.map { measurable ->
            //Measure each child
            measurable.measure(constraints)
        }

        if (placeables.size != 2) {
            throw RuntimeException("AroundRow illegality")
        }
        val maxHeight = max(placeables[0].height,placeables[1].height)
        layout(
            constraints.maxWidth,
            maxHeight
        ){
            placeables[0].placeRelative(startPadding.roundToPx(),(maxHeight - placeables[0].height)/2)
            placeables[1].placeRelative(
                constraints.maxWidth - placeables[1].width - endPadding.roundToPx(),
                (maxHeight - placeables[1].height)/2
            )
        }
    }
}
