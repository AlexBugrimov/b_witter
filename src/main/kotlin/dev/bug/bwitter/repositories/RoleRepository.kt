package dev.bug.bwitter.repositories

import dev.bug.bwitter.entities.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoleRepository: JpaRepository<Role, Long> {

    fun findByAuthority(authority: String): Optional<Role>
}