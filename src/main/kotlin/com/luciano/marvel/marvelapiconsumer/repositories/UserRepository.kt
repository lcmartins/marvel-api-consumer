package com.luciano.marvel.marvelapiconsumer.repositories

import com.luciano.marvel.marvelapiconsumer.documents.ApiUser
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserRepository: ReactiveMongoRepository<ApiUser, String> {
    fun findByLogin(login: String): Mono<ApiUser>
}