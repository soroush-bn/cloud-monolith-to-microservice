package com.example.monolith.controller

import com.example.monolith.entity.UserEntity
import com.example.monolith.model.UserAndArticleResponse
import com.example.monolith.model.UserAndCommentResponse
import com.example.monolith.model.UserWithCommentsAndArticles
import com.example.monolith.service.UserService
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
        model["title"] = "test"
        return "test"
    }

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    fun postUser(@RequestBody user: UserEntity): UserEntity = service.register(user)


    @PatchMapping
    fun updateUser(@RequestBody user: UserEntity): UserEntity = service.updateUser(user)

    @DeleteMapping("delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@RequestParam(name = "id") userId: Long): Unit = service.deleteUser(userId)

    @GetMapping("userWithComments")
    fun getUserWithComments(@RequestParam(name = "id") userId: Long): UserAndCommentResponse =
        service.getUserWithComments(userId)

    @GetMapping("userWithArticles")
    fun getUserWithArticles(@RequestParam(name = "id") userId: Long): UserAndArticleResponse =
        service.getUserWithArticles(userId)

    @GetMapping("userWithArticlesAndComments")
    fun getUserWithArticlesAndComments(@RequestParam(name = "id") userId: Long): UserWithCommentsAndArticles =
        service.getUserWithArticlesAndComments(userId)

    @ExceptionHandler(NoSuchElementException::class)
    fun idNotFound(e: NoSuchElementException): ResponseEntity<String> = ResponseEntity(e.message, HttpStatus.NOT_FOUND)

}