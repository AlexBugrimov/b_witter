package dev.bug.bwitter.web.controller

import dev.bug.bwitter.exceptions.EmailAlreadyTakeException
import dev.bug.bwitter.exceptions.UserDoesNotExistException
import dev.bug.bwitter.mapper.UserMapper
import dev.bug.bwitter.services.UserService
import dev.bug.bwitter.web.models.UpdatingRequest
import dev.bug.bwitter.web.models.UserRequest
import dev.bug.bwitter.web.models.UserResponse
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
    fun registerUser(@RequestBody request: UserRequest): ResponseEntity<UserResponse> {
        val applicationUser = userMapper.toApplicationUser(request)
        val newUser = userService.registerUser(applicationUser)
        return ResponseEntity(userMapper.toUserResponse(newUser), HttpStatus.CREATED)
    }

    @ExceptionHandler(UserDoesNotExistException::class)
    fun handleUserDoesntExists(): ResponseEntity<String> {
        return ResponseEntity("The user you are looking for doesn't exist", HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{username}")
    fun updateUser(@PathVariable("username") username: String,
                   @RequestBody request: UpdatingRequest
    ): ResponseEntity<UserResponse> {
        val applicationUser = userMapper.toApplicationUser(request)
        val updatedUser = userService.updateUser(username, applicationUser)
        return ResponseEntity(userMapper.toUserResponse(updatedUser), HttpStatus.OK)
    }
}