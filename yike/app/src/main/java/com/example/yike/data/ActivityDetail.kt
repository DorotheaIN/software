package com.example.yike.data

import com.example.yike.R

data class ActivityDetail(
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