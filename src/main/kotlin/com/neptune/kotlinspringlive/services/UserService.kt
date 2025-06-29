package com.neptune.kotlinspringlive.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.neptune.kotlinspringlive.entity.User
import com.neptune.kotlinspringlive.repository.UserRepository
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val repository: UserRepository,
    private val redis: ReactiveRedisTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {

    private val log = LoggerFactory.getLogger(this.javaClass)
    suspend fun getUserByUsername(username: String): User? {
        val value = redis.opsForValue().get(username).awaitSingleOrNull()
        log.info("get redis value $value")
        if (StringUtils.isNotEmpty(value)) {
            return objectMapper.readValue(value!!.trim(), User::class.java)
        }
        val user = repository.findByUsername(username)
        if (Objects.nonNull(user)) {
            val value = objectMapper.writeValueAsString(user)
            log.info("serialize user: $value")
            redis.opsForValue().set(username, value, java.time.Duration.ofMinutes(5)).awaitSingleOrNull()
        }
        return user
    }
}