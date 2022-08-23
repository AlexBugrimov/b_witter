package dev.bug.bwitter.mapper

import dev.bug.bwitter.entities.ApplicationUser
import dev.bug.bwitter.web.models.RegistrationRequest
import dev.bug.bwitter.web.models.RegistrationResponse
import org.springframework.stereotype.Component

@Component
class UserMapper {

    fun toApplicationUser(request: RegistrationRequest): ApplicationUser =
        ApplicationUser(
            request.firstName,
            request.lastName,
            request.email,
            request.birthDate)

    fun toRegistrationResponse(applicationUser: ApplicationUser): RegistrationResponse =
        RegistrationResponse(
            applicationUser.userId,
            applicationUser.firstName,
            applicationUser.lastName,
            applicationUser.email,
            applicationUser.phone,
            applicationUser.birthDate,
            applicationUser.username,
            applicationUser.authorities
        )
}