package me.mottet.axon.query.cart

import me.mottet.axon.domain.cart.CartCreated
import me.mottet.axon.domain.cart.ProductAdded
import org.axonframework.eventhandling.EventHandler
import org.axonframework.eventhandling.Timestamp
import org.axonframework.messaging.annotation.MetaDataValue
import org.axonframework.queryhandling.QueryHandler
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class CartProjector(val cartDetailRepository: CartDetailRepository) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @EventHandler
    fun on(cartCreated: CartCreated, @Timestamp timestamp: Instant) {
        logger.debug("projector {}", cartCreated)
        cartDetailRepository.save(CartDetail(cartCreated.id, cartCreated.userId, 0.0, 0, listOf(), timestamp.toEpochMilli()))
    }

    @EventHandler
    fun on(productAdded: ProductAdded , @Timestamp timestamp: Instant) {
        logger.debug("projector {}", productAdded)
        val cartDetail = cartDetailRepository.findByIdOrNull(productAdded.id)
                ?: throw Exception("Cart not found: ${productAdded.id}")
        val numberOfProduct = cartDetail.numberOfProduct + productAdded.quantity
        val products = cartDetail.products + Product(productAdded.name, productAdded.quantity, productAdded.unitPrice)
        val totalPrice = cartDetail.totalPrice + (productAdded.quantity * productAdded.unitPrice)
        cartDetailRepository.save(CartDetail(cartDetail.id, cartDetail.userId, totalPrice, numberOfProduct, products, timestamp.toEpochMilli()))
    }

    @QueryHandler
    fun handle(query: CartDetailQuery) = cartDetailRepository.findByIdOrNull(query.id)
            ?: throw Exception("Cart not found: ${query.id}")
}