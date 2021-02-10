package com.luciano.marvel.marvelapiconsumer.gateways

import com.luciano.marvel.marvelapiconsumer.domain.ApiResult
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.math.BigInteger
import java.security.MessageDigest
import java.time.LocalDateTime

@Component
class MarvelApiGatewayImpl : MarvelApiGateway {

    @Value("\${marvel.api.characters}")
    val gatewayUrl: String = ""

    override suspend fun getCharacters(): Mono<ApiResult> {
        return WebClient.create()
            .get()
            .uri(getUrl())
            .retrieve()
            .bodyToMono(ApiResult::class.java)
    }

    override suspend fun getImage(url: String): Mono<ByteArray> {
        return WebClient.create()
            .get()
            .uri(url)
            .accept(MediaType.IMAGE_JPEG)
            .retrieve()
            .bodyToMono(ByteArray::class.java)
    }

    private fun getUrl(): String {
        val timeStamp = LocalDateTime.now().toString()
        val hash = generateMD5(timeStamp)
        return "$gatewayUrl?ts=$timeStamp&apikey=${System.getenv("APP_KEY")}&hash=$hash"
    }

    private fun generateMD5(timeStamp: String): String {
        val input = timeStamp + System.getenv("APP_SECRET") + System.getenv("APP_KEY")
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}