package me.mottet.axon.query

import me.mottet.axon.query.cart.CartKeyListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.listener.PatternTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories


@Configuration
@Profile("query")
@ComponentScan
@EnableRedisRepositories
class QueryConfiguration {

    @Bean
    fun keyListenerContainer(connectionFactory: RedisConnectionFactory, keyListener: CartKeyListener) =
            RedisMessageListenerContainer().apply {
                setConnectionFactory(connectionFactory)
                addMessageListener(keyListener, PatternTopic("__keyevent@*__:hset"))
            }
}