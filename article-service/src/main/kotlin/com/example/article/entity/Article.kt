package com.example.article.entity

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.time.OffsetDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
data class Article(
    var slug: String = "",
    var title: String = "",
    var description: String = "",
    var body: String = "",
    var userId: Long,
    var createdAt: OffsetDateTime = OffsetDateTime.now(),
    var updatedAt: OffsetDateTime = OffsetDateTime.now(),
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var articleId: Long = 0
)
