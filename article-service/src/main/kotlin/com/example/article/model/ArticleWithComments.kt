package com.example.article.model

import com.example.article.entity.Article

data class ArticleWithComments(
    val article: Article,
    val comments: Comments
)
