package com.example.yike.data

import com.example.yike.R

data class Organization(
    val name:String,
    val img:Int,
    val describtion:String
)

val SSE = Organization(
    "同济软件",
    R.drawable.sse,
    ""
)

val TJELE = Organization(
    "同济电信",
    R.drawable.tjelec,
    ""
)

val TJSC = Organization(
    "同济创新创业",
    R.drawable.tjsc,
    ""
)

val TJStudent = Organization(
    "同济大学生",
    R.drawable.tjstudent,
    ""
)