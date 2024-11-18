package dev.zbib.userservice.service;

import dev.zbib.userservice.model.entity.User;
import dev.zbib.userservice.model.request.UserRequest;
import dev.zbib.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRequest createUser(User user) {
        return userRepository.save(user);
    }
}
