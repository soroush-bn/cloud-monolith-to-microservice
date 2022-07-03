package com.example.monolith.entity

import java.time.OffsetDateTime
import javax.persistence.*

@Entity
data class CommentEntity(
    var createdAt: OffsetDateTime = OffsetDateTime.now(),
    var updatedAt: OffsetDateTime = OffsetDateTime.now(),
    var body: String = "",
    @ManyToOne var article: ArticleEntity = ArticleEntity(),
    @ManyToOne var author: UserEntity = UserEntity(),
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0
)