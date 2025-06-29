package com.neptune.kotlinspringlive.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfiguration {

    @Bean
    fun reactiveRedisTemplate(
        reactiveRedisConnectionFactory: ReactiveRedisConnectionFactory,
        objectMapper: ObjectMapper,
    ): ReactiveRedisTemplate<String, Any> {
//        // 创建并配置ObjectMapper
//        val objectMapper = Jackson2ObjectMapperBuilder.json()
//            .serializationInclusion(JsonInclude.Include.NON_NULL) // 忽略null值
//            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
//            .modules(JavaTimeModule(), KotlinModule.Builder().build())
//            .build<>()
        // 使用Jackson2JsonRedisSerializer而不是默认的Jdk序列化
        val valueSerializer = Jackson2JsonRedisSerializer(objectMapper, Any::class.java)

        val serializationContext = RedisSerializationContext.newSerializationContext<String, Any>()
            .key(StringRedisSerializer())
            .value(valueSerializer)
            .hashKey(StringRedisSerializer())
            .hashValue(valueSerializer)
            .build()

        return ReactiveRedisTemplate(reactiveRedisConnectionFactory, serializationContext)
    }
}