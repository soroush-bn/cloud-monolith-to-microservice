package com.example.article.model

import java.time.OffsetDateTime

data class Comment(
    var createdAt: OffsetDateTime = OffsetDateTime.now(),
    var updatedAt: OffsetDateTime = OffsetDateTime.now(),
    var body: String = "",
    var userId: Long = 0,
    var articleId: Long = 0,
    var id: Long = 0
)
