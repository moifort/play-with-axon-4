package me.mottet.axon.client

import me.mottet.axon.query.cart.CartDetail
import me.mottet.axon.query.cart.CartDetailQuery
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component
import org.springframework.web.socket.messaging.SessionDisconnectEvent
import org.springframework.web.socket.messaging.SessionSubscribeEvent
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent

@Component
class CartDetailSubcription(val queryGateway: QueryGateway,
                            val websocket: SimpMessagingTemplate) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @EventListener
    fun onSubscribeEvent(event: SessionSubscribeEvent) {
        val destination = event.message.headers["simpDestination"] as String
        if (destination.startsWith("/topic/cart/")) {
            val cartId = destination.replace("/topic/cart/", "")
            logger.debug("Subscribe to cartId: $cartId")
            val subscriptionQuery = queryGateway.subscriptionQuery(
                    CartDetailQuery(cartId),
                    ResponseTypes.instanceOf(CartDetail::class.java),
                    ResponseTypes.instanceOf(CartDetail::class.java))
            subscriptionQuery.handle(
                    { result -> websocket.convertAndSend(destination, result) },
                    { result -> websocket.convertAndSend(destination, result) })
        }
    }

    @EventListener
    fun onSubscribeEvent(event: SessionUnsubscribeEvent) {
        logger.debug("SessionUnsubscribeEvent: $event")

    }

    @EventListener
    fun onSubscribeEvent(event: SessionDisconnectEvent) {
        logger.debug("SessionDisconnectEvent: $event")
    }

}