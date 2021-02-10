package com.luciano.marvel.marvelapiconsumer.requestbodies

import javax.validation.constraints.NotEmpty

class UserPostRequest(@NotEmpty(message = "{field.name.required}") val name: String,
                      @NotEmpty(message = "{field.password.required}") var password: String,
                      @NotEmpty(message = "{field.login.required}") val login: String) {}