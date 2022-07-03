package com.example.monolith.service

import com.example.monolith.entity.CommentEntity
import com.example.monolith.model.Comments
import com.example.monolith.repository.ArticleRepository
import com.example.monolith.repository.CommentRepository
import com.example.monolith.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*


@Service
class CommentService(
    val commentRepository: CommentRepository,
    val userRepository: UserRepository,
    val articleRepository: ArticleRepository
) {

    fun getComment(id: Long): Optional<CommentEntity> = commentRepository.findById(id)

    fun getCommentsOfUser(userId: Long): Comments {

        val user = userRepository.findById(userId).get()
        val commentlist = commentRepository.findAllByAuthor(user)
        return Comments(commentlist)

    }

    fun getCommentsOfArticle(articleId: Long): Comments {

        val article = articleRepository.findByArticleId(articleId)
        val commentlist = commentRepository.findAllByArticle(article)
        return Comments(commentlist)

    }


    fun post(comment: CommentEntity): CommentEntity = commentRepository.save(comment)

    fun updateComment(i: Int, comment: CommentEntity): CommentEntity = commentRepository.save(comment)

    fun deleteComment(id: Long) = commentRepository.deleteById(id)
}