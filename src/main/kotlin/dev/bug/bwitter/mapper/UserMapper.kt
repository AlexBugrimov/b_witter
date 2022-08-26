package dev.bug.bwitter.mapper

import dev.bug.bwitter.entities.ApplicationUser
import dev.bug.bwitter.web.models.Request
import dev.bug.bwitter.web.models.UserResponse
import org.springframework.stereotype.Component

@Component
class UserMapper {

    fun toApplicationUser(request: Request): ApplicationUser =
        ApplicationUser(
            firstName = request.firstName,
            lastName = request.lastName,
            email = request.email,
            birthDate = request.birthDate,
            phone = request.phone
        )

    fun toUserResponse(applicationUser: ApplicationUser): UserResponse =
        UserResponse(
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