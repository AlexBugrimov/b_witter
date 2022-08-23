package dev.bug.bwitter.web.models

import dev.bug.bwitter.entities.Role
import java.time.LocalDate

data class RegistrationResponse(
    val userId: Long?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String?,
    val birthDate: LocalDate,
    val username: String?,
    val authorities: MutableSet<Role>
)
