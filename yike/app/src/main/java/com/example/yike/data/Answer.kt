package com.example.yike.data

data class Answer(val question:String, val answerContent:String,val comment:String,val commentUser:String)

object AnswerData{
    val answer = Answer("你怎么看待复读？",
        "本人山东考生，2020年高考第一次参加高考，总分674，全省位次873，被北京航空航天大学中法工程师学院录取。北航确实是个好大学，也是我梦想中大学的样子，中法也可以说是中国中外合作办学的典范，但是由于自己不适应中法的培养模式，我在半个月后选择退学。莫笑农家腊酒浑，丰年留客足鸡豚。山重水复疑无路，柳暗花明又一村。箫鼓追随春社近，衣冠简朴古风存。从今若许闲乘月，拄杖无时夜叩门","","")
}

object CommentData{
    private const val question = "你怎么看待复读？"
    private const val answerContent = "本人山东考生，2020年高考第一次参加高考，总分674，全省位次873，被北京航空航天大学中法工程师学院录取。北航确实是个好大学，也是我梦想中大学的样子，中法也可以说是中国中外合作办学的典范，但是由于自己不适应中法的培养模式，我在半个月后选择退学。"

    val comment = listOf(
        Answer(question,answerContent,"我们开始更新啦","王瀚林"),
        Answer(question,answerContent, "为了给广大的读者一个更好的体验，从今天起，我们公众号决定陆续发一些其他作者的高质量文章","时守格"),
        Answer(question,answerContent,"每逢佳节倍思亲，从今天起，参加我们公众号活动的同学可以获得精美礼品一份！！","赵敏"),
        Answer(question,answerContent,"荣华梦一场，功名纸半张，是非海波千丈，马蹄踏碎禁街霜，听几度头鸡唱","狄载源"),
        Answer(question,answerContent, "唤归来，西湖山上野猿哀。二十年多少风流怪，花落花开。望云霄拜将台，袖星斗安邦策，破烟月迷魂寨。酸斋笑我，我笑酸斋","邓若涛"),
        Answer(question,answerContent, "伤心尽处露笑颜，醉里孤单写狂欢。两路殊途情何奈，三千弱水忧忘川。花开彼岸朦胧色，月过长空爽朗天。青鸟思飞无侧羽，重山万水亦徒然","陈一帆"),
        Answer(question,answerContent, "又到绿杨曾折处，不语垂鞭，踏遍清秋路。衰草连天无意绪，雁声远向萧关去。恨天涯行役苦，只恨西风，吹梦成今古。明日客程还几许，沾衣况是新寒雨","李芷若"),
        Answer(question,answerContent, "莫笑农家腊酒浑，丰年留客足鸡豚。山重水复疑无路，柳暗花明又一村。箫鼓追随春社近，衣冠简朴古风存。从今若许闲乘月，拄杖无时夜叩门","洪楷贤")
    )
}