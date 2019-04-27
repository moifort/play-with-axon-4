package me.mottet.axon.query

import me.mottet.axon.domain.cart.CartCreated
import me.mottet.axon.domain.cart.ProductAdded
import org.axonframework.eventhandling.EventHandler
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Profile("query")
@Component
class CartProjector(val cartDetailRepository: CartDetailRepository) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @EventHandler
    fun on(cartCreated: CartCreated) {
        logger.debug("projector {}", cartCreated)
        cartDetailRepository.save(CartDetail(cartCreated.id, cartCreated.userId, 0))
    }

    @EventHandler
    fun on(productAdded: ProductAdded) {
        logger.debug("projector {}", productAdded)
    }
}