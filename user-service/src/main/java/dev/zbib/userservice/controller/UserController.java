package dev.zbib.userservice.controller;

import dev.zbib.userservice.model.mappers.UserMapper;
import dev.zbib.userservice.model.request.UserRequest;
import dev.zbib.userservice.model.response.UserResponse;
import dev.zbib.userservice.model.entity.User;
import dev.zbib.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        User user = UserMapper.toUser(userRequest);

        User savedUser = userService.createUser(user);
        UserResponse userResponse = UserMapper.toUserResponse(savedUser);

        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
}
