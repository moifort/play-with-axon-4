package me.mottet.axon

import me.mottet.axon.client.ClientConfiguration
import me.mottet.axon.domain.DomainConfiguration
import me.mottet.axon.query.QueryConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@EnableAutoConfiguration
@Import(QueryConfiguration::class, DomainConfiguration::class, ClientConfiguration::class)
class AxonApplication

fun main(args: Array<String>) {
    runApplication<AxonApplication>(*args)
}
