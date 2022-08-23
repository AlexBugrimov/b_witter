package dev.bug.bwitter.services

import kotlin.random.Random

class Username(
    private val name: String
) {

    fun generate(): String = "${name}${Random(Long.MAX_VALUE).nextLong()}"
}
