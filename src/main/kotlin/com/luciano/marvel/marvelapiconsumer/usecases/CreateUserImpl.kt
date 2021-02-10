package com.luciano.marvel.marvelapiconsumer.usecases

import com.luciano.marvel.marvelapiconsumer.documents.ApiUser
import com.luciano.marvel.marvelapiconsumer.repositories.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class CreateUserImpl(private val userRepository: UserRepository): CreateUser {
    override fun execute(apiUser: ApiUser): Mono<ApiUser> {
        apiUser.id = UUID.randomUUID().toString()
        apiUser.pwd = BCryptPasswordEncoder().encode(apiUser.pwd)
        return this.userRepository.save(apiUser)
    }
}