package dev.bug.bwitter.web.models

import java.time.LocalDate

data class RegistrationRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val birthDate: LocalDate)
