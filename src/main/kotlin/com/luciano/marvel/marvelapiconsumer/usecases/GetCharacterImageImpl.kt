package com.luciano.marvel.marvelapiconsumer.usecases

import com.luciano.marvel.marvelapiconsumer.domain.ImageSize
import com.luciano.marvel.marvelapiconsumer.gateways.MarvelApiGateway
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class GetCharacterImageImpl(val apiGateway: MarvelApiGateway) : GetCharacterImage {
    override suspend fun getImage(url: String, size: ImageSize): Mono<ByteArray> {
        val imageUrl = "$url/${size.portraitType()}.jpg"
        return apiGateway.getImage(imageUrl)
    }
}