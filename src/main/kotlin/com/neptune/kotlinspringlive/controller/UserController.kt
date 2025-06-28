package com.neptune.kotlinspringlive.controller

import com.neptune.kotlinspringlive.entity.User
import com.neptune.kotlinspringlive.services.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val service: UserService) {

    val logger: Logger = LoggerFactory.getLogger(UserController::class.java);

    @GetMapping("/user/{username}")
    suspend fun getUser(@PathVariable("username") username: String): User? {
        logger.info("this is kotlin controller return${username}")
        return service.getUserByUsername(username)
    }
}