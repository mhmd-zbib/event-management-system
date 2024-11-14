package dev.zbib.userservice.controller;

import dev.zbib.userservice.entity.User;
import dev.zbib.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestParam Long userId,
                                           @RequestParam String firstName,
                                           @RequestParam String lastName,
                                           @RequestParam String phoneNumber,
                                           @RequestParam String password,
                                           @RequestParam LocalDate birthDate,
                                           @RequestParam String profilePicture,
                                           @RequestParam String history,
                                           @RequestParam String address) {
        User createdUser = userService.createUser(userId
                , firstName
                , lastName
                , phoneNumber
                , password
                , birthDate
                , profilePicture
                , history
                , address);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
