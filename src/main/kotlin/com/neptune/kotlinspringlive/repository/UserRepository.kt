package com.neptune.kotlinspringlive.repository

import com.neptune.kotlinspringlive.entity.User
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: CoroutineCrudRepository<User, Long>{

    suspend fun findByUsername(username: String): User?
}