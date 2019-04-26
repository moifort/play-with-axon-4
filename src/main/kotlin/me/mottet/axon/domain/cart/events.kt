package me.mottet.axon.domain.cart

import java.util.*

data class ProductAdded(val id: UUID, val productName: String, val quantity: Int)
data class ProductRemoved(val id: UUID, val productName: String, val quantity: Int)
data class CartCreated(val id: UUID, val userId: UUID)