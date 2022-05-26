package com.cloud.user.service.service

import com.cloud.user.service.entity.UserEntity
import com.cloud.user.service.model.Comment
import com.cloud.user.service.model.Comments
import com.cloud.user.service.model.UserAndCommentResponse
import com.cloud.user.service.model.UserWithCommentAndArticle
import com.cloud.user.service.repository.UserRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.util.*
import kotlin.collections.List


@Service
@Slf4j
class UserService @Autowired constructor(
    private val repository: UserRepository,
    private val restTemplate: RestTemplate
) {
    fun getUser(id: Long): Optional<UserEntity> = repository.findById(id)

    fun post(user: UserEntity): UserEntity = repository.save(user)

    fun updateUser(i: Int, user: UserEntity): UserEntity = repository.save(user)

    fun deleteUser(id: Long) = repository.deleteById(id)
    fun getUserWithComment(userId: Long): UserAndCommentResponse {
        val user = repository.findById(userId).get()
        val comments =
             restTemplate.getForObject("http://localhost:9001/comment/commentsof/" + user.id, ) ?: Comments(
                emptyList()
            )
        comments
        return UserAndCommentResponse(user, comments)
    }

//    fun getUserWithArticlesAndComments(userId: Long): UserWithCommentAndArticle {
//        val user = repository.findById(userId).get()
////        val comments = restTemplate.getForObject("http://localhost:9001/comments/"+user.id, ParameterizedTypeReference<List<Comment>>::class.java)?: Comment()
//
////        return UserAndCommentResponse(user, comment)
//
//    }
}