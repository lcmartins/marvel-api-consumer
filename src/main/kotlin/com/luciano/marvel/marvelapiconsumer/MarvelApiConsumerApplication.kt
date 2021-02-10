package com.luciano.marvel.marvelapiconsumer

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.web.reactive.config.EnableWebFlux

@SpringBootApplication
@EnableWebFlux
@EnableAutoConfiguration(exclude = [WebMvcAutoConfiguration::class])
class MarvelApiConsumerApplication

fun main(args: Array<String>) {
    runApplication<MarvelApiConsumerApplication>(*args)
}
