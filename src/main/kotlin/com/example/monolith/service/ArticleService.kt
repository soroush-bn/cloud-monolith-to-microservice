package com.example.monolith.service

import com.example.monolith.entity.ArticleEntity
import com.example.monolith.model.ArticleWithComments
import com.example.monolith.model.Articles
import com.example.monolith.model.Comments
import com.example.monolith.repository.ArticleRepository
import com.example.monolith.repository.CommentRepository
import com.example.monolith.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class ArticleService(
    val articleRepository: ArticleRepository,
    val userRepository: UserRepository,
    val commentRepository: CommentRepository
) {


    fun saveArticle(article: ArticleEntity): ArticleEntity {

        return articleRepository.save(article)
    }

    fun findArticleById(articleId: Long): ArticleEntity {

        return articleRepository.findByArticleId(articleId)
    }

    fun getArticlesOfUser(id: Long): Articles {

        val user = userRepository.findById(id).get()
        return Articles(articles = articleRepository.findAllByAuthor(user))
    }

    fun getArticleWithComments(id: Long): ArticleWithComments {

        val article = articleRepository.findByArticleId(id)
        val comments = Comments(commentRepository.findAllByArticle(article))
        return ArticleWithComments(article = article, comments = comments)
    }


}