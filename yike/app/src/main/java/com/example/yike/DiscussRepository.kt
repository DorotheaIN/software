package com.example.yike

/**
 * [DiscussRepository] serves as the model layer for our home screen. This allows the user to
 * request themes and garden items.
 *
 * We use an interface so that we can create multiple implementations and swap them out.
 */
interface DiscussRepository {
    //suspend: 提醒调用者需要在协程中使用
    suspend fun fetchDiscussInThemes(): List<DiscussTheme>
    suspend fun fetchDiscussInItems(): List<DiscussTheme>
}