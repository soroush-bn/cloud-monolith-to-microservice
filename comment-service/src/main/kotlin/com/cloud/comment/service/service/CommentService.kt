package com.cloud.comment.service.service

import com.cloud.comment.service.entity.Comment
import com.cloud.comment.service.model.Comments
import com.cloud.comment.service.repository.CommentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
class CommentService(@Autowired val repository: CommentRepository) {
    fun getComment(id: Long): Optional<Comment> = repository.findById(id)
    fun getCommentsOfUser(userId: Long): Comments {

        val commentlist = repository.findAllByUserId(userId)
        return Comments(commentlist)

    }

    fun getCommentsOfArticle(articleId: Long): Comments {

        val commentlist = repository.findAllByArticleId(articleId)
        return Comments(commentlist)

    }


    fun post(comment: Comment): Comment = repository.save(comment)

    fun updateComment(i: Int, comment: Comment): Comment = repository.save(comment)

    fun deleteComment(id: Long) = repository.deleteById(id)
}