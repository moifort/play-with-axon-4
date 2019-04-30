package me.mottet.axon.query

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.listener.PatternTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.stereotype.Component


@Configuration
@Profile("query")
@ComponentScan
@EnableRedisRepositories
class QueryConfiguration {
    @Bean
    fun keyExpirationListenerContainer(connectionFactory: RedisConnectionFactory, expirationListener: KeyNotificationListener): RedisMessageListenerContainer {
        val listenerContainer = RedisMessageListenerContainer()
        listenerContainer.setConnectionFactory(connectionFactory)
        listenerContainer.addMessageListener(expirationListener, PatternTopic("__keyevent@*__:*"))
        return listenerContainer
    }
}

@Component
class KeyNotificationListener : MessageListener {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun onMessage(message: Message, pattern: ByteArray?) {
        val key = String(message.body)
        logger.debug("key: {}", key)
    }

}