package me.mottet.axon.client

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("client")
@ComponentScan("me.mottet.axon.client")
class Configuration