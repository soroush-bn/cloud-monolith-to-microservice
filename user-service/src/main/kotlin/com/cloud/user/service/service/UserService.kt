package com.cloud.user.service.service

import com.cloud.user.service.entity.UserEntity
import com.cloud.user.service.model.*
import com.cloud.user.service.repository.UserRepository
import com.cloud.user.service.utils.JwtUtil
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject


@Service
class UserService constructor(
    private val repository: UserRepository, private val restTemplate: RestTemplate, private val jwtUtil: JwtUtil
) {

    fun register(user: UserEntity): UserEntity {
        user.token = jwtUtil.generateToken(user.id.toString())
        return repository.save(user)
    }

    fun login(userLogin: UserLogin): String {
        // todo check password if valid generate token then return token
        return jwtUtil.generateToken(userLogin.username)
    }

    fun updateUser(user: UserEntity): UserEntity = repository.save(user)

    fun deleteUser(id: Long) = repository.deleteById(id)

    fun getUserWithComments(userId: Long): UserAndCommentResponse {
        val user = repository.findById(userId).get()
        val comments = restTemplate.getForObject(
            "http://comment-service/comment/commentsOfUser?id=" + user.id, Comments::class.java
        ) ?: Comments(
            emptyList()
        )
        return UserAndCommentResponse(user, comments)
    }

    fun getUserWithArticles(userId: Long): UserAndArticleResponse {
        val user = repository.findById(userId).get()
        val articles =
            restTemplate.getForObject("http://article-service/article/articlesOfUser?id=" + user.id) ?: Articles(
                emptyList()
            )
        return UserAndArticleResponse(user, articles)
    }

    fun getUserWithArticlesAndComments(userId: Long): UserWithCommentsAndArticles {
        val user = repository.findById(userId).get()
        val articles =
            restTemplate.getForObject("http://article-service/article/articlesOfUser?id=" + user.id) ?: Articles(
                emptyList()
            )
        val comments =
            restTemplate.getForObject("http://comment-service/comment/commentsOfUser?id=" + user.id) ?: Comments(
                emptyList()
            )
        return UserWithCommentsAndArticles(user, comments, articles)
    }


}
