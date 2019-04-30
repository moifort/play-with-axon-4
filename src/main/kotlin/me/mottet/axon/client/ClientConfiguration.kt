package me.mottet.axon.client

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@Profile("client")
@EnableSwagger2
@ComponentScan
class ClientConfiguration