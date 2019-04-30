package me.mottet.axon.query.cart

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("cart")
data class CartDetail(@Id val id: String, val userId: String, val productCount: Int)