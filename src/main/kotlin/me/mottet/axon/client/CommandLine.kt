package me.mottet.axon.client

import me.mottet.axon.domain.cart.AddProduct
import me.mottet.axon.query.cart.CartDetail
import me.mottet.axon.query.cart.CartDetailQuery
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component

@Component
class CommandLine(val queryGateway: QueryGateway,
                  val commandGateway: CommandGateway,
                  val websocket: SimpMessagingTemplate) : CommandLineRunner {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun run(vararg args: String?) {
        val subsicriptionQuery = queryGateway.subscriptionQuery(
                CartDetailQuery("mycart"),
                ResponseTypes.instanceOf(CartDetail::class.java),
                ResponseTypes.instanceOf(CartDetail::class.java))

        subsicriptionQuery.handle(
                { result -> websocket.convertAndSend("/topic/greetings", result) },
                { result -> websocket.convertAndSend("/topic/greetings", result) })

        commandGateway.sendAndWait<String>(AddProduct("mycart", "mars", 2.0, 1))
    }
}