package com.example.yike.data

import com.example.yike.R

data class User(
    val name:String,
    val introduction:String,
    val pic:Int
)

val Taylor =User(
    "Taylor Swift",
    "taylorswift13",
    R.drawable.tay
)