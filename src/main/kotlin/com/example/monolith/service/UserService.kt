package com.example.monolith.service

import com.example.monolith.entity.UserEntity
import com.example.monolith.model.*
import com.example.monolith.repository.ArticleRepository
import com.example.monolith.repository.CommentRepository
import com.example.monolith.repository.UserRepository
import org.springframework.stereotype.Service


@Service
class UserService constructor(
    val userRepository: UserRepository,
    val commentRepository: CommentRepository,
    val articleRepository: ArticleRepository
) {

    fun register(user: UserEntity): UserEntity {

        return userRepository.save(user)
    }


    fun updateUser(user: UserEntity): UserEntity = userRepository.save(user)

    fun deleteUser(id: Long) = userRepository.deleteById(id)

    fun getUserWithComments(userId: Long): UserAndCommentResponse {
        val user = userRepository.findById(userId).get()
        val comments = Comments(commentRepository.findAllByAuthor(user))
        return UserAndCommentResponse(user, comments)
    }

    fun getUserWithArticles(userId: Long): UserAndArticleResponse {
        val user = userRepository.findById(userId).get()
        val articles = Articles(articleRepository.findAllByAuthor(user))
        return UserAndArticleResponse(user, articles)
    }

    fun getUserWithArticlesAndComments(userId: Long): UserWithCommentsAndArticles {
        val user = userRepository.findById(userId).get()
        val comments = Comments(commentRepository.findAllByAuthor(user))
        val articles = Articles(articleRepository.findAllByAuthor(user))
        return UserWithCommentsAndArticles(user, comments, articles)
    }


}

