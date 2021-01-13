package com.luciano.marvel.marvelapiconsumer.usecases

import com.luciano.marvel.marvelapiconsumer.domain.ImageSize
import reactor.core.publisher.Mono

interface GetCharacterImage {
    suspend fun getImage(url: String, size: ImageSize): Mono<ByteArray>
}