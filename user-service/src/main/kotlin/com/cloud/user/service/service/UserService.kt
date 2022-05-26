package com.cloud.user.service.service

import com.cloud.user.service.entity.UserEntity
import com.cloud.user.service.model.*
import com.cloud.user.service.repository.UserRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.util.*


@Service
@Slf4j
class UserService @Autowired constructor(
    private val repository: UserRepository, private val restTemplate: RestTemplate
) {
    fun getUser(id: Long): Optional<UserEntity> = repository.findById(id)

    fun post(user: UserEntity): UserEntity = repository.save(user)

    fun updateUser( user: UserEntity): UserEntity = repository.save(user)

    fun deleteUser(id: Long) = repository.deleteById(id)
    fun getUserWithComments(userId: Long): UserAndCommentResponse {
        val user = repository.findById(userId).get()
        val comments = restTemplate.getForObject("http://localhost:9001/comment/commentsOfUser/" + user.id) ?: Comments(
            emptyList()
        )
        return UserAndCommentResponse(user, comments)
    }

    fun getUserWithArticles(userId: Long): UserAndArticleResponse {
        val user = repository.findById(userId).get()
        val articles = restTemplate.getForObject("http://localhost:9003/article/articlesOfUser/" + user.id) ?: Articles(
            emptyList()
        )
        return UserAndArticleResponse(user, articles)
    }

    fun getUserWithArticlesAndComments(userId: Long): UserWithCommentsAndArticles {
        val user = repository.findById(userId).get()
        val articles = restTemplate.getForObject("http://localhost:9003/article/articlesOfUser/" + user.id) ?: Articles(
            emptyList()
        )
        val comments = restTemplate.getForObject("http://localhost:9001/comment/commentsOfUser/" + user.id) ?: Comments(
            emptyList()
        )
        return UserWithCommentsAndArticles(user, comments,articles)
    }

//    fun getUserWithArticlesAndComments(userId: Long): UserWithCommentAndArticle {
//        val user = repository.findById(userId).get()
////        val comments = restTemplate.getForObject("http://localhost:9001/comments/"+user.id, ParameterizedTypeReference<List<Comment>>::class.java)?: Comment()
//
////        return UserAndCommentResponse(user, comment)
//
//    }
}