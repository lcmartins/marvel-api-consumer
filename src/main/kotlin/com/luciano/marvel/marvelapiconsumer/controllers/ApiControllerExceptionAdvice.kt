package com.luciano.marvel.marvelapiconsumer.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.status
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
@ControllerAdvice
class ApiControllerExceptionAdvice {

    @ExceptionHandler(value = [BadCredentialsException::class])
    fun adviceBadCredentials(exception: Throwable): ResponseEntity<HttpStatus> =
        status(HttpStatus.FORBIDDEN).build()
}