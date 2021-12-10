package com.example.yike.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.yike.DiscussTheme
import com.example.yike.R
import com.example.yike.defaultDiscussThemes
import com.example.yike.ui.theme.YikeTheme
import com.example.yike.viewModel.QTheme

//尝试复用
@Composable
fun ThemeCard(
    theme: QTheme,
    onClick: ()-> Unit = {}
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .size(136.dp)
            .clickable {
                run(onClick)
            },
    ) {
        Column {
            Image(
//                painterResource(id = theme.img),
                painterResource(id = R.drawable.fiddle_leaf),
                contentDescription = "${theme.title} Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(136.dp)
                    .height(96.dp)
            )
//            Text(
//                text = themes.title,
//                style = MaterialTheme.typography.h6,
//                modifier = Modifier
//                    .paddingFromBaseline(top = 24.dp)
//                    .padding(horizontal = 16.dp),
//            )
        }
    }
}

//@Preview(
//    name = "Night Mode",
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//)
//@Preview(
//    name = "Day Mode",
//    uiMode = Configuration.UI_MODE_NIGHT_NO,
//)
//@Composable
//private fun PlantThemeCardPreview() {
//    YikeTheme {
//        ThemeCard(
//            themes = defaultDiscussThemes.first()
//        )
//    }
//}