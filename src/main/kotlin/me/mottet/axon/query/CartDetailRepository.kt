package me.mottet.axon.query

import org.springframework.context.annotation.Profile
import org.springframework.data.repository.CrudRepository
import java.util.*

@Profile("query")
interface CartDetailRepository : CrudRepository<CartDetail, UUID>