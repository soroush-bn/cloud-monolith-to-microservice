package com.example.article.controller

import com.example.article.entity.Article
import com.example.article.model.ArticleWithComments
import com.example.article.model.Articles
import com.example.article.service.ArticleService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("article/")
class ArticleController(
    val articleService: ArticleService
) {
    @PostMapping
    fun saveArticle(@RequestBody article: Article): Article {

        return articleService.saveArticle(article)
    }

    @GetMapping("{id}")
    fun getArticle(@PathVariable("id") articleId: Long): Article {

        return articleService.findArticleById(articleId)
    }

    @GetMapping("articlesOfUser/{id}")
    fun getArticlesOfUser(@PathVariable id: Long) : Articles = articleService.getArticlesOfUser(id)


    @GetMapping("articleWithComments/{id}")
    fun getArticleWithComments(@PathVariable id: Long) : ArticleWithComments = articleService.getArticleWithComments(id)


}