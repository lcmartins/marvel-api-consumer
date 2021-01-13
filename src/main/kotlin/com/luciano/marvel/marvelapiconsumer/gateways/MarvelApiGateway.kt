package com.luciano.marvel.marvelapiconsumer.gateways

import com.luciano.marvel.marvelapiconsumer.domain.ApiResult
import com.luciano.marvel.marvelapiconsumer.domain.MarvelCharacter
import reactor.core.publisher.Mono

interface MarvelApiGateway {
    suspend fun getCharacters(): Mono<ApiResult>
    suspend fun getImage(url: String): Mono<ByteArray>
}