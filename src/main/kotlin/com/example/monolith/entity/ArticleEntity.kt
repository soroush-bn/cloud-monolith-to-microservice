package com.example.monolith.entity

import java.time.OffsetDateTime
import javax.persistence.*

@Entity
data class ArticleEntity(
    var slug: String = "",
    var title: String = "",
    var description: String = "",
    var body: String = "",
    @ManyToOne var author: UserEntity = UserEntity(),
    var createdAt: OffsetDateTime = OffsetDateTime.now(),
    var updatedAt: OffsetDateTime = OffsetDateTime.now(),
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var articleId: Long = 0
)
