package me.mottet.axon.domain

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("command")
@ComponentScan("me.mottet.axon.domain")
class Configuration