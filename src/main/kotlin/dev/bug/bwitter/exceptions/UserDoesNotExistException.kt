package dev.bug.bwitter.exceptions

class UserDoesNotExistException(username: String): RuntimeException("The user $username does not exist")