package me.mottet.axon.domain.cart

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class AddProduct(@TargetAggregateIdentifier val id: String, val productName: String, val quantity: Int)
data class RemoveProduct(@TargetAggregateIdentifier val id: String, val productName: String, val quantity: Int)
data class CreateCart(@TargetAggregateIdentifier val id: String, val userId: String)
