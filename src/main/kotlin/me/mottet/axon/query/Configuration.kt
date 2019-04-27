package me.mottet.axon.query

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("query")
@ComponentScan("me.mottet.axon.query")
class Configuration