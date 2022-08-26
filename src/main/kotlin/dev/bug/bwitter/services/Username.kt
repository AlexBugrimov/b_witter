package dev.bug.bwitter.services

import java.util.function.Predicate
import kotlin.Long.Companion.MAX_VALUE
import kotlin.random.Random

class Username(
    private val name: String,
    private val usernameNotExists: Predicate<String>
) {

    fun create(): String {
        do {
            val username = "${name}${Random(MAX_VALUE).nextLong()}"
            if (usernameNotExists.test(username)) return username
        } while (true)
    }
}
