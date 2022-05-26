package com.cloud.service.registery

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class ServiceRegisteryApplication

fun main(args: Array<String>) {
	runApplication<ServiceRegisteryApplication>(*args)
}
