package dev.bug.bwitter.repositories

import dev.bug.bwitter.entities.ApplicationUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: JpaRepository<ApplicationUser, Long> {

    fun findByUsername(username: String): Optional<ApplicationUser>
}