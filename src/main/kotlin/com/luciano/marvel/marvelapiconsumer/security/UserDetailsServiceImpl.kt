package com.luciano.marvel.marvelapiconsumer.security

import com.luciano.marvel.marvelapiconsumer.documents.ApiUser
import com.luciano.marvel.marvelapiconsumer.repositories.UserRepository
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Service
class UserDetailsServiceImpl(private val userRepository: UserRepository) : ReactiveUserDetailsService {

    override fun findByUsername(username: String): Mono<UserDetails> {
        val user = userRepository.findByLogin(username)
        return user.map {
            User.builder()
                .username(it.login)
                .password(it.pwd)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .authorities("USER")
                .disabled(false)
                .roles("USER")
                .build()
        }
    }

    fun findAll(): Flux<ApiUser> {
        return userRepository.findAll()
    }
}