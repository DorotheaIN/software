package com.example.yike.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.example.yike.data.ActivityDetail
import com.example.yike.ui.screens.ActivityDetailDisplayScreen
import com.example.yike.ui.theme.YikeTheme

class ActivityDetailActivity:ComponentActivity (){
    companion object{
        fun navigate(context: Context){
            val intent = Intent(context,ActivityDetailActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        setContent {
//            ActivityDetailDisplayScreen()
        }
    }
}

@Composable
fun ActivityDetailPage(){
    YikeTheme {
//        ActivityDetailDisplayScreen()
    }
}