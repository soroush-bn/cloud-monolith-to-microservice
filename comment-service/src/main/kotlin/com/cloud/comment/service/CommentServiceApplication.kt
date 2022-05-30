package com.cloud.comment.service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class CommentServiceApplication

fun main(args: Array<String>) {
	runApplication<CommentServiceApplication>(*args)
}
