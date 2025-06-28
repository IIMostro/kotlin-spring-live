package com.neptune.kotlinspringlive.services

import com.neptune.kotlinspringlive.entity.User
import com.neptune.kotlinspringlive.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository) {


    suspend fun getUserById(id: Long): User? {
        return repository.findById(id)
    }

    suspend fun getUserByUsername(username: String): User? {
        return repository.findByUsername(username)
    }
}