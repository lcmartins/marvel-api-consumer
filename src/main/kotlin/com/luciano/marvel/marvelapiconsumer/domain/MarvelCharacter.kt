package com.luciano.marvel.marvelapiconsumer.domain

data class MarvelCharacter(val id: Long,
                           val name: String,
                           val description: String,
                           val thumbnail: Thumbnail)
