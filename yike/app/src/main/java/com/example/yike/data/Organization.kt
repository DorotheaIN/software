package com.example.yike.data

import com.example.yike.R

data class Organization(
    val name:String,
    val img:Int,
    val describtion:String
)

val SSE = Organization(
    "同济小软件",
    R.drawable.sse,
    "同济大学软件学院学生会"
)

val TJELE = Organization(
    "同济电信",
    R.drawable.sse,
    "xx"
)

val TJSC = Organization(
    "同济创新创业",
    R.drawable.sse,
    "xx"
)

val TJStudent = Organization(
    "同济大学生",
    R.drawable.sse,
    "xx"
)