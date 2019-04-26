package me.mottet.axon.domain.cart

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

data class AddProduct(@TargetAggregateIdentifier val id: UUID, val productName: String, val quantity: Int)
data class RemoveProduct(@TargetAggregateIdentifier val id: UUID, val productName: String, val quantity: Int)
data class CreateCart(@TargetAggregateIdentifier val id: UUID, val userId: UUID)
