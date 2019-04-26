package me.mottet.axon.domain.cart

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.spring.stereotype.Aggregate
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import java.util.*

@Profile("command")
@Aggregate
class Cart {
    private val logger = LoggerFactory.getLogger(javaClass)

    @AggregateIdentifier
    private lateinit var id: UUID

    constructor()

    @CommandHandler
    constructor(createCart: CreateCart) {
        logger.debug("handling {}", createCart)
        apply(CartCreated(createCart.id, createCart.userId))
    }

    @CommandHandler
    fun addProduct(addProduct: AddProduct) {
        logger.debug("handling {}", addProduct)
        apply(ProductAdded(addProduct.id, addProduct.productName, addProduct.quantity))
    }


    @EventSourcingHandler
    fun on(cartCreated: CartCreated) {
        logger.debug("apply {}", cartCreated)
        id = cartCreated.id
    }

    @EventSourcingHandler
    fun on(productAdded: ProductAdded) {
        logger.debug("apply {}", productAdded)
    }

}