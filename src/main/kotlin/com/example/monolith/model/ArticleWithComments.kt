package com.example.monolith.model

import com.example.monolith.entity.ArticleEntity


data class ArticleWithComments(
    val article: ArticleEntity,
    val comments: Comments
)
