package me.mottet.axon.query

import org.springframework.data.repository.CrudRepository
import java.util.*

interface CartDetailRepository : CrudRepository<CartDetail, UUID>