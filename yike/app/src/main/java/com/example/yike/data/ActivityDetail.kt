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
    val genres:String,
    var likeNum:Int,
    val capacity:Int,
    var status:Int,
    var subscriberList:List<Int>,
    var evaluationList:List<Int>
)

fun getActivityDetail(id:Int) : ActivityDetail? {
    for(i in activityDetailList){
        if(i.id == id){
            return i
        }
    }
    return null
}

val test = ActivityDetail(
    1,
    "数据库入门",
    SSE,
    R.drawable.database,
    "11月19日",
    "腾讯会议",
    "线上会议",
    "IBM俱乐部&TOSA 本周六下午15：30将联合举办一次讲座，主要内容是数据库入门和进阶，与一些相对前沿的简介，时长约1.5-2小时，主讲人是我们软院2020年毕业生付旭炜（mwish）学长，届时欢迎加入线上会议。\n" +
            "本次讲座不需要数据库基础，可以在下学期学习数据库之前先对数据库技术有一定的了解，对数据库感兴趣的同学一定不要错过这次讲座哦！",
    "1.第一部分：传统的RDBMS " +
            "2.第二部分：NOSQL",
    "主讲人：付旭炜（mwish）\n" +
            "同济大学软件学院 2020 届毕业生\n" +
            "字节跳动 ByteGraph 研发工程师\n" +
            "TiKV contributor",
    "学术,软件学院,数据库",
    1,
    2,
    1,
    listOf(1),
    listOf(1)
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
        "IBM俱乐部&TOSA 本周六下午15：30将联合举办一次讲座，主要内容是数据库入门和进阶，与一些相对前沿的简介，时长约1.5-2小时，主讲人是我们软院2020年毕业生付旭炜（mwish）学长，届时欢迎加入线上会议。\n" +
                "本次讲座不需要数据库基础，可以在下学期学习数据库之前先对数据库技术有一定的了解，对数据库感兴趣的同学一定不要错过这次讲座哦！",
        "1.第一部分：传统的RDBMS " +
                "2.第二部分：NOSQL",
        "主讲人：付旭炜（mwish）\n" +
                "同济大学软件学院 2020 届毕业生\n" +
                "字节跳动 ByteGraph 研发工程师\n" +
                "TiKV contributor",
        "学术,软件学院,数据库",
        1,
        2,
        1,
        listOf(1),
        listOf(1)
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
        "学术,软件学院,数据库",
        1,
        2,
        1,
        listOf(1),
        listOf(1)
    ),
    ActivityDetail(
        2,
        "济学外语",
        SSE,
        R.drawable.language,
        "11月19日",
        "腾讯会议",
        "线上会议",
        "xxxxxx",
        "xxxxx",
        "不需要数据库基础",
        "学术,软件学院,数据库",
        1,
        2,
        1,
        listOf(1),
        listOf(1)
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
        "学术,软件学院,数据库",
        1,
        2,
        1,
        listOf(1),
        listOf(1)
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
        "学术,软件学院,数据库",
        1,
        2,
        1,
        listOf(1),
        listOf(1)
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
        "学术,软件学院,数据库",
        1,
        2,
        1,
        listOf(1),
        listOf(1)
    ),
    ActivityDetail(
        8,
        "济学外语",
        SSE,
        R.drawable.language,
        "11月19日",
        "腾讯会议",
        "线上会议",
        "xxxxxx",
        "xxxxx",
        "不需要数据库基础",
        "学术,软件学院,数据库",
        1,
        2,
        1,
        listOf(1),
        listOf(1)
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
        "学术,软件学院,数据库",
        1,
        2,
        1,
        listOf(1),
        listOf(1)
    ),

)