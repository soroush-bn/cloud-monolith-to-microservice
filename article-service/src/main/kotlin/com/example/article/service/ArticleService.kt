package com.example.article.service

import com.example.article.entity.Article
import com.example.article.model.ArticleWithComments
import com.example.article.model.Articles
import com.example.article.model.Comments
import com.example.article.repository.ArticleRepository
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Service
class ArticleService(
    val articleRepository: ArticleRepository, private val restTemplate: RestTemplate
) {


    fun saveArticle(article: Article): Article {

        return articleRepository.save(article)
    }

    fun findArticleById(articleId: Long): Article {

        return articleRepository.findByArticleId(articleId)
    }

    fun getArticlesOfUser(id: Long): Articles {

        return Articles(articles = articleRepository.findAllByUserId(id))
    }

    fun getArticleWithComments(id: Long): ArticleWithComments {
        val article = articleRepository.findByArticleId(id)
        val comments = restTemplate.getForObject("http://localhost:9001/comment/commentsOfArticle/" + article.articleId)
            ?: Comments(
                emptyList()
            )

        return ArticleWithComments(article = article, comments = comments)
    }


}