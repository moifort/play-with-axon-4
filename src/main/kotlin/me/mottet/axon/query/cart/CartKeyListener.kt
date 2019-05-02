package me.mottet.axon.query.cart

import org.axonframework.queryhandling.QueryUpdateEmitter
import org.slf4j.LoggerFactory
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class CartKeyListener(val queryUpdateEmitter: QueryUpdateEmitter, val cartDetailRepository: CartDetailRepository) : MessageListener {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun onMessage(message: Message, pattern: ByteArray?) {
        val key = String(message.body)
        if (key.startsWith("cart")) {
            logger.debug("listen: {}", key)
            val cartId = key.replace("cart:", "")
            val cartDetail = cartDetailRepository.findByIdOrNull(cartId) ?: throw Exception("Cart not found: $cartId")
            queryUpdateEmitter.emit(CartDetailQuery::class.java, { query -> cartId == query.id }, cartDetail)
        }
    }
}