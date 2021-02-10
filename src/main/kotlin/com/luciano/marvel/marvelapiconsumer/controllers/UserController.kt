package com.luciano.marvel.marvelapiconsumer.controllers

import com.luciano.marvel.marvelapiconsumer.documents.ApiUser
import com.luciano.marvel.marvelapiconsumer.requestbodies.CredentialsPostRequest
import com.luciano.marvel.marvelapiconsumer.requestbodies.UserPostRequest
import com.luciano.marvel.marvelapiconsumer.security.JWTService
import com.luciano.marvel.marvelapiconsumer.security.UserDetailsServiceImpl
import com.luciano.marvel.marvelapiconsumer.usecases.CreateUserImpl
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/users")
class UserController(
    val createUserImpl: CreateUserImpl,
    val userDetailsServiceImpl: UserDetailsServiceImpl,
    val JWTService: JWTService,
    val authenticationManager: ReactiveAuthenticationManager
) {
    @PostMapping("signupq")
    fun create(@RequestBody @Valid userPostRequest: UserPostRequest): ResponseEntity<Mono<ApiUser>> {
        val userCreated = createUserImpl.execute(toEntity(userPostRequest))
        return ResponseEntity(userCreated, HttpStatus.CREATED)
    }

    @PostMapping("auth")
    fun authenticate(@RequestBody credentialsPostRequest: CredentialsPostRequest): Mono<ResponseEntity<MutableMap<String, String?>>> {
        return Mono.just(credentialsPostRequest)
            .flatMap { form ->
                authenticationManager
                    .authenticate(UsernamePasswordAuthenticationToken(form.login, form.password))
                    .map(this.JWTService::createToken)
            }
            .map { jwt ->
                val httpHeaders = HttpHeaders()
                httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer $jwt")
                val tokenBody = java.util.Map.of("access_token", jwt)
                ResponseEntity(tokenBody, httpHeaders, HttpStatus.OK)
            }
    }

    fun toEntity(userPostRequest: UserPostRequest): ApiUser {
        return ApiUser(
            UUID.randomUUID().toString(),
            userPostRequest.name,
            userPostRequest.password,
            userPostRequest.login
        )
    }
}