package com.cloud.comment.service.controller

import com.cloud.comment.service.entity.Comment
import com.cloud.comment.service.model.Comments
import com.cloud.comment.service.service.CommentService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("comment/")
@Slf4j
class CommentController(@Autowired val service: CommentService) {

    @GetMapping
    fun getMain(model: Model): String {
        model["title"] = "test"
        return "test"
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun idNotFound(e: NoSuchElementException): ResponseEntity<String> = ResponseEntity(e.message, HttpStatus.NOT_FOUND)


    @GetMapping("getComment")
    fun getComment(@RequestParam(name = "id") id: Long): Optional<Comment> = service.getComment(id)

    @GetMapping("commentsOfUser")
    fun getCommentsOfUser(@RequestParam(name = "id") id: Long): Comments = service.getCommentsOfUser(id)

    @GetMapping("commentsOfArticle")
    fun getCommentsOfArticle(@RequestParam(name = "id") id: Long): Comments = service.getCommentsOfArticle(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postComment(@RequestBody comment: Comment): Comment = service.post(comment)

    @PatchMapping
    fun updateComment(@RequestBody comment: Comment): Comment = service.updateComment(1, comment)

    @DeleteMapping("delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteComment(@RequestParam(name = "id") id: Long): Unit = service.deleteComment(id)

}