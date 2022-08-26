package dev.bug.bwitter.web.models

import java.time.LocalDate

data class UserRequest(
    override val firstName: String,
    override val lastName: String,
    override val email: String,
    override val birthDate: LocalDate): Request(firstName, lastName, email, birthDate, null)
