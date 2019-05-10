package me.mottet.axon.client

import me.mottet.axon.domain.cart.AddProduct
import me.mottet.axon.domain.cart.CreateCart
import me.mottet.axon.query.cart.CartDetail
import me.mottet.axon.query.cart.CartDetailQuery
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import java.util.*

@CrossOrigin
@RestController
@RequestMapping("cart")
class CartController(val commandGateway: CommandGateway, val queryGateway: QueryGateway) {

    @GetMapping("/create")
    fun createCart(@RequestParam(required = false) cartId: String = UUID.randomUUID().toString(),
                   @RequestParam userId: String) =
            commandGateway.sendAndWait<String>(CreateCart(cartId, userId))

    @GetMapping("/add")
    fun addProduct(@RequestParam cartId: String,
                   @RequestParam name: String,
                   @RequestParam unitPrice: Double,
                   @RequestParam quantity: Int) =
            commandGateway.sendAndWait<String>(AddProduct(cartId, name, unitPrice, quantity))

    @GetMapping("/{id}/detail")
    fun detail(@PathVariable("id") cartId: String) =
            queryGateway.query(CartDetailQuery(cartId), ResponseTypes.instanceOf(CartDetail::class.java)).join()

    @GetMapping("/{id}/subscribe", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun subscribe(@PathVariable("id") cartId: String): Flux<CartDetail> {
        val subscriptionQuery = queryGateway.subscriptionQuery(
                CartDetailQuery(cartId),
                ResponseTypes.instanceOf(CartDetail::class.java),
                ResponseTypes.instanceOf(CartDetail::class.java))
        return subscriptionQuery.updates().mergeWith(subscriptionQuery.initialResult())
    }

}