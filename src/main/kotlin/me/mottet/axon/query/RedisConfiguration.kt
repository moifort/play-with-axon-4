package me.mottet.axon.query

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

@Profile("query")
@Configuration
@EnableRedisRepositories
class RedisConfiguration