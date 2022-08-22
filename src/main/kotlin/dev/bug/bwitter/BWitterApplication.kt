package dev.bug.bwitter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BWitterApplication

fun main(args: Array<String>) {
    runApplication<BWitterApplication>(*args)
}
