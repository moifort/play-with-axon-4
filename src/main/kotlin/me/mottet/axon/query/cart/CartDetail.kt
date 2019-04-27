package me.mottet.axon.query.cart

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.util.*

@RedisHash("cart")
data class CartDetail(@Id val id: UUID, val userId: UUID, val productCount: Int)