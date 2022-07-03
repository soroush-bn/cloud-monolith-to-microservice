package com.example.monolith.repository


import com.example.monolith.entity.ArticleEntity
import com.example.monolith.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository : JpaRepository<ArticleEntity, Long> {

    fun findByArticleId(articleId: Long): ArticleEntity


    fun findAllByAuthor(user: UserEntity): List<ArticleEntity>


}