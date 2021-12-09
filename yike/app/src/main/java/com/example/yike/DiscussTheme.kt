package com.example.yike


// 假数据
data class DiscussTheme(
    val imageRes: Int,
    val title: String,
)

val defaultDiscussThemes = listOf(
    DiscussTheme(R.drawable.desert_chic, "Desert chic"),
    DiscussTheme(R.drawable.tiny_terrariums, "Tiny terrariums"),
    DiscussTheme(R.drawable.jungle_vibes, "Jungle vibes"),
    DiscussTheme(R.drawable.easy_care, "Easy care"),
    DiscussTheme(R.drawable.statements, "Statements")
)

