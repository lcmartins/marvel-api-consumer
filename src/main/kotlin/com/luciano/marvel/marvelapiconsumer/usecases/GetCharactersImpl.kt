package com.luciano.marvel.marvelapiconsumer.usecases

import com.luciano.marvel.marvelapiconsumer.domain.ApiResult
import com.luciano.marvel.marvelapiconsumer.gateways.MarvelApiGateway
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class GetCharactersImpl(val apiGateway: MarvelApiGateway) : GetCharacters {
    override suspend fun retrieve(): Mono<ApiResult> {
        return apiGateway.getCharacters()
    }
}