package dev.bug.bwitter.services

import dev.bug.bwitter.entities.ApplicationUser
import dev.bug.bwitter.entities.Role
import dev.bug.bwitter.exceptions.EmailAlreadyTakeException
import dev.bug.bwitter.exceptions.UserDoesNotExistException
import dev.bug.bwitter.repositories.RoleRepository
import dev.bug.bwitter.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository,
    val roleRepository: RoleRepository
) {

    companion object {
        private const val USER_AUTHORITY = "USER"
    }

    fun registerUser(user: ApplicationUser): ApplicationUser {
        val roles: MutableSet<Role> = user.authorities
        roles.add(roleRepository.findByAuthority(USER_AUTHORITY).get())

        user.username = Username(user.firstName + user.lastName)
            { userRepository.findByUsername(it).isEmpty }.create()

        user.authorities.addAll(roles)
        try {
            return userRepository.save(user)
        } catch (ex: Exception) {
            throw EmailAlreadyTakeException()
        }
    }

    fun updateUser(username: String, applicationUser: ApplicationUser): ApplicationUser {
        val user: ApplicationUser = getUserByUsername(username)

        applicationUser.phone?.run { user.phone = this }
        applicationUser.email?.run { user.email = this }
        applicationUser.firstName?.run { user.firstName = this }
        applicationUser.lastName?.run { user.lastName = this }

        try {
          return userRepository.save(user)
        } catch (ex: Exception) {
            throw EmailAlreadyTakeException()
        }
    }

    fun getUserByUsername(username: String): ApplicationUser {
        return userRepository.findByUsername(username)
            .orElseThrow { UserDoesNotExistException(username) }
    }

    fun generateEmailVerification(username: String) {
        val user = getUserByUsername(username)
        user.verification = generateVerificationNumber()
        userRepository.save(user)
    }

    private fun generateVerificationNumber(): Long = (0 .. Long.MAX_VALUE).random()
}