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

val defaultDiscussItems = listOf(
    DiscussTheme(R.drawable.monstera, "Monstera"),
    DiscussTheme(R.drawable.aglaonema, "Aglaonema"),
    DiscussTheme(R.drawable.peace_lily, "Peace lily"),
    DiscussTheme(R.drawable.fiddle_leaf, "Fiddle leaf tree"),
    DiscussTheme(R.drawable.snake_plant, "Snake plant"),
    DiscussTheme(R.drawable.pothos, "Pothos"),
)