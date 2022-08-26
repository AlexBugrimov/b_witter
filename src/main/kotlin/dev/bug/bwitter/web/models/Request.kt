package dev.bug.bwitter.web.models

import java.time.LocalDate

abstract class Request(
    open val firstName: String?,
    open val lastName: String?,
    open val email: String?,
    open val birthDate: LocalDate?,
    open val phone: String?
) {
}