package me.mottet.axon.domain.cart

data class ProductAdded(val id: String, val name: String, val unitPrice: Double, val quantity: Int)
data class ProductRemoved(val id: String, val name: String, val quantity: Int)
data class CartCreated(val id: String, val userId: String)