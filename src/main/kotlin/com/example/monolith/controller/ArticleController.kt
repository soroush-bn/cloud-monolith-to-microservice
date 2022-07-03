package com.example.monolith.controller

import com.example.monolith.entity.ArticleEntity
import com.example.monolith.model.ArticleWithComments
import com.example.monolith.model.Articles
import com.example.monolith.service.ArticleService
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("article/")
class ArticleController(
    val articleService: ArticleService
) {

    @GetMapping
    fun getMain(model: Model): String {
        model["title"] = "test"
        return "test"
    }

    @PostMapping
    fun saveArticle(@RequestBody article: ArticleEntity): ArticleEntity {

        return articleService.saveArticle(article)
    }

    @GetMapping("getArticle")
    fun getArticle(@RequestParam(name = "id") articleId: Long): ArticleEntity {

        return articleService.findArticleById(articleId)
    }

    @GetMapping("articlesOfUser")
    fun getArticlesOfUser(@RequestParam(name = "id") id: Long): Articles = articleService.getArticlesOfUser(id)


    @GetMapping("articleWithComments")
    fun getArticleWithComments(@RequestParam(name = "id") id: Long): ArticleWithComments =
        articleService.getArticleWithComments(id)


}