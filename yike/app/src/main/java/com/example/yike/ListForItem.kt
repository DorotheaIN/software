package com.example.yike

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.outlined.QuestionAnswer
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.yike.ui.theme.YikeTheme
import com.example.yike.viewModel.Question

@Composable
fun ListForItem(item: Question) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Column {
            TitleDescriptionCheckboxRow(item)

            Divider()
        }
    }
}

@Composable
private fun TitleDescriptionCheckboxRow(item: Question) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TitleAndDescription(item)

        MyIcon(item)
    }
}

@Composable
private fun MyIcon(item: Question) {
//    val checkedState = remember {
//        mutableStateOf(false)
//    }
    Column() {
        Icon(
            Icons.Outlined.Star,
            contentDescription = "Follow",
            modifier = Modifier
                .size(24.dp)
        )

        Text(text = item.followNum.toString())

        Icon(
            Icons.Outlined.QuestionAnswer,
            contentDescription = "Answer",
            modifier = Modifier
                .size(24.dp)
        )

        Text(text = item.answerNum.toString())
    }
//    Checkbox(
//        checked = checkedState.value,
//        onCheckedChange = { isChecked ->
//            checkedState.value = isChecked
//        },
//        colors = CheckboxDefaults.colors(
//            checkmarkColor = MaterialTheme.colors.background,
//        ),
//        modifier = Modifier
//            .size(24.dp)
//    )
}

@Composable
private fun RowScope.TitleAndDescription(item: Question) {
    //RowScope : 接口调用weight
    Column(
        modifier = Modifier
            .weight(1F)
    ) {
        Text(
            text = item.title,
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .paddingFromBaseline(top = 24.dp)
        )
//        Text(
//            text = item.description,
//            style = MaterialTheme.typography.h6,
//            modifier = Modifier
//                .paddingFromBaseline(top = 24.dp))

//        Text(
//            text = "This is a description",
//            style = MaterialTheme.typography.body1,
//            modifier = Modifier
//                .paddingFromBaseline(bottom = 24.dp),
//        )
    }
}

//@Composable
//private fun MyImage(discussTheme: DiscussTheme) {
//    Image(
//        painterResource(id = discussTheme.imageRes),
//        contentDescription = "${discussTheme.title} Image",
//        contentScale = ContentScale.Crop,
//        modifier = Modifier
//            .size(64.dp)
//            .clip(MaterialTheme.shapes.small),
//    )
//}

//@Preview(
//    name = "Night Mode",
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//)
//@Preview(
//    name = "Day Mode",
//    uiMode = Configuration.UI_MODE_NIGHT_NO,
//)
//@Composable
//private fun DiscussListItemPreview() {
//    YikeTheme {
//        Surface(
//            color = MaterialTheme.colors.background
//        ) {
//            ListForItem(item = defaultDiscussItems.first())
//        }
//    }
//}