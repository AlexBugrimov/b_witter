package dev.bug.bwitter.exceptions

class EmailAlreadyTakeException(message: String = "The email provided is already taken") : RuntimeException(message) {
}