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
        model["title"] = "salam sag"
        return "sag"
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun idNotFound(e: NoSuchElementException): ResponseEntity<String> = ResponseEntity(e.message, HttpStatus.NOT_FOUND)


    @GetMapping("{id}")
    fun getComment(@PathVariable id: Long): Optional<Comment> = service.getComment(id)

    @GetMapping("commentsof/{id}")
    fun getComments(@PathVariable id: Long): Comments = service.getComments(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postComment(@RequestBody comment: Comment): Comment = service.post(comment)

    @PatchMapping
    fun updateComment(@RequestBody comment: Comment):Comment = service.updateComment(1,comment)

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteComment(@PathVariable id: Long) :Unit= service.deleteComment(id)

}