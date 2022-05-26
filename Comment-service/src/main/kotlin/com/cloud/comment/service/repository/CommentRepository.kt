package com.cloud.comment.service.repository

import com.cloud.comment.service.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface CommentRepository : JpaRepository<Comment,Long>{
    fun findAllByUserId(userId: Long) : List<Comment>
    fun findAllByArticleId(articleId: Long) : List<Comment>
}