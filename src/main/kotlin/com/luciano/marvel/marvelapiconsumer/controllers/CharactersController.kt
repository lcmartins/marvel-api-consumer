package com.luciano.marvel.marvelapiconsumer.controllers

import com.luciano.marvel.marvelapiconsumer.documents.ApiUser
import com.luciano.marvel.marvelapiconsumer.domain.ImageSize
import com.luciano.marvel.marvelapiconsumer.domain.MarvelCharacter
import com.luciano.marvel.marvelapiconsumer.repositories.UserRepository
import com.luciano.marvel.marvelapiconsumer.usecases.GetCharacterImage
import com.luciano.marvel.marvelapiconsumer.usecases.GetCharacters
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/characters")
class CharactersController(
    val getCharactersUseCase: GetCharacters,
    val getCharacterImageUseCase: GetCharacterImage,
    val userRepository: UserRepository
) {

    @GetMapping
    suspend fun get(): ResponseEntity<Mono<List<MarvelCharacter>>> {
        val list = getCharactersUseCase.retrieve().map { it.data.results }
        return ResponseEntity(list, HttpStatus.OK)
    }

    @GetMapping("/image")
    suspend fun getImage(@RequestParam url: String, @RequestParam size: ImageSize): ResponseEntity<Mono<ByteArray>> {
        val resource = getCharacterImageUseCase.getImage(url, size)
        val headers = HttpHeaders()
        headers.add("Content-disposition", "inline; filename=image.jpg")
        headers.add("Content-type", "image/jpeg")
        return ResponseEntity(resource, headers, HttpStatus.OK)
    }

    @PostMapping("/login")
    fun doLogin(@RequestBody apiUser: ApiUser): ResponseEntity<Unit> {
        return ResponseEntity.noContent().build<Unit>()
    }
}
