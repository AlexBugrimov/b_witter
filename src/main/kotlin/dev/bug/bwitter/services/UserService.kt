package dev.bug.bwitter.services

import dev.bug.bwitter.entities.ApplicationUser
import dev.bug.bwitter.entities.Role
import dev.bug.bwitter.exceptions.EmailAlreadyTakeException
import dev.bug.bwitter.repositories.RoleRepository
import dev.bug.bwitter.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository,
    val roleRepository: RoleRepository
) {

    fun registerUser(user: ApplicationUser): ApplicationUser {
        val roles: MutableSet<Role> = user.authorities
        roles.add(roleRepository.findByAuthority("USER").get())

        user.username = generateName(user.firstName + user.lastName)
        user.authorities.addAll(roles)
        try {
            return userRepository.save(user)
        } catch (ex: Exception) {
            throw EmailAlreadyTakeException()
        }
    }

    private fun generateName(name: String): String {
        do {
            val username = Username(name).generate()
            if (userRepository.findByUsername(username).isEmpty) {
                 return username
            }
        } while (true)
    }
}