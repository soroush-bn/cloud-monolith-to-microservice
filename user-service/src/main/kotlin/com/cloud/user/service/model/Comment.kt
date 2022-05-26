package com.cloud.user.service.model

import java.time.OffsetDateTime
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

data class Comment(var createdAt: OffsetDateTime = OffsetDateTime.now(),
                   var updatedAt: OffsetDateTime = OffsetDateTime.now(),
                   var body: String = "",
//                   @ManyToOne
//                   var article: Article = Article(),
//                   @ManyToOne
//                   var author: User = User(),
                   var userId: Long=0,
                   var articleId: Long=0,
                   var id: Long = 0)
