package com.cloud.user.service.controller

import com.cloud.user.service.entity.UserEntity
import com.cloud.user.service.model.UserAndCommentResponse
import com.cloud.user.service.model.UserWithCommentAndArticle
import com.cloud.user.service.service.UserService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("user/")
@Slf4j
class UserController(@Autowired val service: UserService) {

    @GetMapping
    fun getMain(model: Model): String {
        model["title"] = "salam sag"
        return "sag"
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun idNotFound(e: NoSuchElementException): ResponseEntity<String> = ResponseEntity(e.message, HttpStatus.NOT_FOUND)


//    @GetMapping("/{id}")
//    fun getUser(@PathVariable id: Long): Optional<UserEntity> = service.getUser(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postUser(@RequestBody user: UserEntity): UserEntity = service.post(user)

    @PatchMapping
    fun updateUser(@RequestBody user: UserEntity): UserEntity = service.updateUser(1, user)

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable id: Long): Unit = service.deleteUser(id)

        @GetMapping("userwithcomments/{id}")
    fun getUserWithComments(@PathVariable("id") userId:Long): UserAndCommentResponse =service.getUserWithComment(userId)

//    @GetMapping("userwitharticle/{id}")
//    fun getUserWithArticleAndComment(@PathVariable("id") userId:Long) : UserWithCommentAndArticle=
//        service.getUserWithArticleAndComment(userId)


}