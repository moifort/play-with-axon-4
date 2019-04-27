package me.mottet.axon.client

import me.mottet.axon.domain.cart.AddProduct
import me.mottet.axon.query.cart.CartDetail
import me.mottet.axon.query.cart.CartDetailQuery
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.*


@Component
class Client(val commandGateway: CommandGateway, val queryGateway: QueryGateway) : CommandLineRunner {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun run(vararg args: String) {
//        val clientId = UUID.randomUUID()
//        (1..20).forEach {
//            logger.debug("run: $it")
//            val cartId = UUID.fromString("8eb73ae1-5f26-4aab-81ca-31e51b2727b4")
//
//            //commandGateway.sendAndWait<UUID>(CreateCart(cartId, clientId))
//            commandGateway.sendAndWait<UUID>(AddProduct(cartId, "Pantoufle", 10))
//            commandGateway.sendAndWait<UUID>(AddProduct(cartId, "iMac", 3))
//        }

        val cartId = UUID.fromString("8eb73ae1-5f26-4aab-81ca-31e51b2727b4")
        commandGateway.sendAndWait<UUID>(AddProduct(cartId, "Pantoufle", 3))
        commandGateway.sendAndWait<UUID>(AddProduct(cartId, "iMac", 100))
        val query = queryGateway.query(CartDetailQuery(cartId), ResponseTypes.instanceOf(CartDetail::class.java)).join()
        logger.debug("query {}", query)
    }
}