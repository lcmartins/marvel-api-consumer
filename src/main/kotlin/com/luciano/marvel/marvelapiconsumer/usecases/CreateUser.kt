package com.luciano.marvel.marvelapiconsumer.usecases

import com.luciano.marvel.marvelapiconsumer.documents.ApiUser
import reactor.core.publisher.Mono

interface CreateUser {
    fun execute(apiUser: ApiUser): Mono<ApiUser>
}