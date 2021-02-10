package com.luciano.marvel.marvelapiconsumer.documents

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class ApiUser(@Id var id: String = "", val name: String, var pwd: String, val login: String)
