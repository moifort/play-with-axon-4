package me.mottet.axon.client

import me.mottet.axon.domain.cart.AddProduct
import me.mottet.axon.domain.cart.CreateCart
import org.axonframework.commandhandling.gateway.CommandGateway
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.*

@Component
class Client(val commandGateway: CommandGateway) : CommandLineRunner {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun run(vararg args: String) {


        val clientId = UUID.randomUUID()

        (1..20).forEach {
            logger.debug("run: $it")
            val cartId = UUID.randomUUID()

            commandGateway.sendAndWait<UUID>(CreateCart(cartId, clientId))
            commandGateway.sendAndWait<UUID>(AddProduct(cartId, "Chaussettes", 1))
            commandGateway.sendAndWait<UUID>(AddProduct(cartId, "Pantalon", 1))
        }
    }
}