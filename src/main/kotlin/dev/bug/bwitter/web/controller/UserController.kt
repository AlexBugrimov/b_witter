package dev.bug.bwitter.web.controller

import dev.bug.bwitter.exceptions.EmailAlreadyTakeException
import dev.bug.bwitter.mapper.UserMapper
import dev.bug.bwitter.services.UserService
import dev.bug.bwitter.web.models.RegistrationRequest
import dev.bug.bwitter.web.models.RegistrationResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    val userService: UserService,
    val userMapper: UserMapper
) {

    @ExceptionHandler(EmailAlreadyTakeException::class)
    fun handleEmailTaken(): ResponseEntity<String> {
        return ResponseEntity("The email you provided is already is use", HttpStatus.CONFLICT)
    }

    @PostMapping
    fun registerUser(@RequestBody request: RegistrationRequest): ResponseEntity<RegistrationResponse> {
        val applicationUser = userMapper.toApplicationUser(request)
        val newUser = userService.registerUser(applicationUser)
        return ResponseEntity(userMapper.toRegistrationResponse(newUser), HttpStatus.CREATED)
    }
}