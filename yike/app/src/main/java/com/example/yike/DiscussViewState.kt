package com.example.yike

/**
 * This data class represents the view state for the home screen. All of this
 * data should be formatted in a way that the home screen can just
 * take the information and display it.
 */
data class DiscussViewState(
    val discussThemes: List<DiscussTheme> = emptyList(),
    val discussItems: List<DiscussTheme> = emptyList(),
) {
    // kotlin中每个属性都自带一个get和set方法 可以自定义
    // 注意要用field替代变量，否则会递归调用
    // var p = ""
    // get() = field
    // set(value) {
    //      field = value
    // }

    // 引用时 调用get方法 此时get返回值 故无需初始化
    val showLoading: Boolean
        get() = discussThemes.isEmpty() || discussItems.isEmpty()
}