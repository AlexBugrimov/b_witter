package dev.bug.bwitter.services

import dev.bug.bwitter.models.ApplicationUser
import dev.bug.bwitter.models.Role
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
        user.authorities.addAll(roles)
        return user
    }
}