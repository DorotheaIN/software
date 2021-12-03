package com.example.yike

import kotlinx.coroutines.delay

class InMemoryDiscussService : DiscussRepository {
    override suspend fun fetchDiscussInThemes(): List<DiscussTheme> {
        return defaultDiscussThemes
    }

    override suspend fun fetchDiscussInItems(): List<DiscussTheme> {
        return defaultDiscussItems
    }
}