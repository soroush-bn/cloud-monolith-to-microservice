package com.example.monolith.repository

import com.example.monolith.entity.ArticleEntity
import com.example.monolith.entity.CommentEntity
import com.example.monolith.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface CommentRepository : JpaRepository<CommentEntity, Long> {

    fun findAllByArticle(article: ArticleEntity): List<CommentEntity>

    fun findAllByAuthor(user: UserEntity): List<CommentEntity>

}