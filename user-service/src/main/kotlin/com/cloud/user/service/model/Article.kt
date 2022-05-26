package com.cloud.user.service.model

import java.time.OffsetDateTime

data class Article(
    var slug: String = "",
    var title: String = "",
    var description: String = "",
    var body: String = "",
    var userId: Long,
    var createdAt: OffsetDateTime = OffsetDateTime.now(),
    var updatedAt: OffsetDateTime = OffsetDateTime.now(),
    var articleId: Long = 0
)
