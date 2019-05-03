package me.mottet.axon.client

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class Socket {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    fun greeting(message: String): String {
        println(message)
        return "Hello, Super tibo!"
    }
}