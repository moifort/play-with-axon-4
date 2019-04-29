package me.mottet.axon.client

import me.mottet.axon.domain.cart.AddProduct
import me.mottet.axon.domain.cart.CreateCart
import me.mottet.axon.query.cart.CartDetail
import me.mottet.axon.query.cart.CartDetailQuery
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
class CartController(val commandGateway: CommandGateway, val queryGateway: QueryGateway) {

    @GetMapping("/create")
    fun createCart() = commandGateway.sendAndWait<UUID>(CreateCart(UUID.randomUUID(), UUID.randomUUID()))

    @GetMapping("/add")
    fun createCart(@RequestParam cartId: String) = commandGateway.sendAndWait<UUID>(AddProduct(UUID.fromString(cartId), "Pantoufle", 3))

    @GetMapping("/detail")
    fun detail() = queryGateway.query(CartDetailQuery(UUID.fromString("8eb73ae1-5f26-4aab-81ca-31e51b2727b4")), ResponseTypes.instanceOf(CartDetail::class.java)).join()
}