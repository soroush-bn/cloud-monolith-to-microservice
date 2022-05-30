package com.cloud.comment.service.entity

import java.time.OffsetDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity
data class Comment(
    var createdAt: OffsetDateTime = OffsetDateTime.now(),
    var updatedAt: OffsetDateTime = OffsetDateTime.now(),
    var body: String = "",
    var userId: Long = 0,
    var articleId: Long = 0,
//                   @ManyToOne
//                   var article: Article = Article(),
//                   @ManyToOne
//                   var author: User = User(),
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0
)