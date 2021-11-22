package com.example.yike.data

import com.example.yike.R

data class ActivityDetail(
    val id:Int,
    val title:String,
    val organizer:Organization,
    val img:Int,
    val date:String,
    val place:String,
    val form:String,
    val introduction:String,
    val content:String,
    val lightening:String,
    val genres:List<String>
)

val test = ActivityDetail(
    1,
    "数据库入门",
    SSE,
    R.drawable.database,
    "11月19日",
    "腾讯会议",
    "线上会议",
    "xxxxxx",
    "xxxxx",
    "不需要数据库基础",
    listOf("学术","软件学院","数据库")
)

val activityDetailList = listOf<ActivityDetail>(
    ActivityDetail(
        0,
        "数据库入门",
        SSE,
        R.drawable.database,
        "11月19日",
        "腾讯会议",
        "线上会议",
        "xxxxxx",
        "xxxxx",
        "不需要数据库基础",
        listOf("学术","软件学院","数据库")
    ),
    ActivityDetail(
        1,
        "嘉定之星",
        TJELE,
        R.drawable.jiaxing,
        "11月19日",
        "腾讯会议",
        "线上会议",
        "xxxxxx",
        "xxxxx",
        "不需要数据库基础",
        listOf("学术","软件学院","数据库")
    ),
    ActivityDetail(
        2,
        "济学外语",
        TJStudent,
        R.drawable.language,
        "11月19日",
        "腾讯会议",
        "线上会议",
        "xxxxxx",
        "xxxxx",
        "不需要数据库基础",
        listOf("学术","软件学院","数据库")
    ),
    ActivityDetail(
        3,
        "社彩之夜",
        TJSC,
        R.drawable.sczy,
        "11月19日",
        "腾讯会议",
        "线上会议",
        "xxxxxx",
        "xxxxx",
        "不需要数据库基础",
        listOf("学术","软件学院","数据库")
    ),
    ActivityDetail(
        4,
        "数据库入门",
        SSE,
        R.drawable.database,
        "11月19日",
        "腾讯会议",
        "线上会议",
        "xxxxxx",
        "xxxxx",
        "不需要数据库基础",
        listOf("学术","软件学院","数据库")
    ),
    ActivityDetail(
        5,
        "嘉定之星",
        TJELE,
        R.drawable.jiaxing,
        "11月19日",
        "腾讯会议",
        "线上会议",
        "xxxxxx",
        "xxxxx",
        "不需要数据库基础",
        listOf("学术","软件学院","数据库")
    ),
    ActivityDetail(
        8,
        "济学外语",
        TJStudent,
        R.drawable.language,
        "11月19日",
        "腾讯会议",
        "线上会议",
        "xxxxxx",
        "xxxxx",
        "不需要数据库基础",
        listOf("学术","软件学院","数据库")
    ),
    ActivityDetail(
        9,
        "社彩之夜",
        TJSC,
        R.drawable.sczy,
        "11月19日",
        "腾讯会议",
        "线上会议",
        "xxxxxx",
        "xxxxx",
        "不需要数据库基础",
        listOf("学术","软件学院","数据库")
    ),

)