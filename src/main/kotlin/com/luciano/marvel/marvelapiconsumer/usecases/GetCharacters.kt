package com.luciano.marvel.marvelapiconsumer.usecases

import com.luciano.marvel.marvelapiconsumer.domain.ApiResult
import reactor.core.publisher.Mono

interface GetCharacters {
    suspend fun retrieve(): Mono<ApiResult>
}