package com.neptune.kotlinspringlive.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.io.Serializable
import java.time.Instant

@Table("users")
data class User(
    @Id val id: Long? = null,
    val username: String? = null,
    val password: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val status: Int? = null,
    val createdAt: Instant? = null,
    val updatedAt: Instant? = null,
    val lastLoginAt: Instant? = null,
): Serializable