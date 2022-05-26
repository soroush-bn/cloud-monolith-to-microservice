package com.example.article.repository

import com.example.article.entity.Article
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository : JpaRepository<Article, Long> {

    fun findByArticleId(articleId: Long): Article

    fun findAllByUserId(userId: Long): List<Article>


}