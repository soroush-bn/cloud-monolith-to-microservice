package com.cloud.user.service.controller

import com.cloud.user.service.entity.UserEntity
import com.cloud.user.service.model.UserAndArticleResponse
import com.cloud.user.service.model.UserAndCommentResponse
import com.cloud.user.service.model.UserLogin
import com.cloud.user.service.model.UserWithCommentsAndArticles
import com.cloud.user.service.service.UserService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("user/")
@Slf4j
class UserController(@Autowired val service: UserService) {

    @GetMapping
    fun getMain(model: Model): String {
        model["title"] = "salam sag"
        return "sag"
    }

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    fun postUser(@RequestBody user: UserEntity): UserEntity = service.register(user)

    @PostMapping("login")
    fun login(@RequestBody userLogin: UserLogin): String {
        return service.login(userLogin)
    }

    @PatchMapping
    fun updateUser(@RequestBody user: UserEntity): UserEntity = service.updateUser(user)

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable id: Long): Unit = service.deleteUser(id)

    @GetMapping("userWithComments/{id}")
    fun getUserWithComments(@PathVariable("id") userId: Long): UserAndCommentResponse =
        service.getUserWithComments(userId)

    @GetMapping("userWithArticles/{id}")
    fun getUserWithArticles(@PathVariable("id") userId: Long): UserAndArticleResponse =
        service.getUserWithArticles(userId)

    @GetMapping("userWithArticlesAndComments/{id}")
    fun getUserWithArticlesAndComments(@PathVariable("id") userId: Long): UserWithCommentsAndArticles =
        service.getUserWithArticlesAndComments(userId)

    @ExceptionHandler(NoSuchElementException::class)
    fun idNotFound(e: NoSuchElementException): ResponseEntity<String> = ResponseEntity(e.message, HttpStatus.NOT_FOUND)

}