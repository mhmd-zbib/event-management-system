package dev.zbib.userservice.service;

import dev.zbib.userservice.entity.User;
import dev.zbib.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(Long userId
            , String firstName
            , String lastName
            , String phoneNumber
            , String password
            , LocalDate birthDate
            , String profilePicture
            , String history
            , String address) {
        User user = User
                .builder()
                .id(userId)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .password(password)
                .birthDate(birthDate)
                .profilePicture(profilePicture)
                .history(history)
                .address(address)
                .build();
        return userRepository.save(user);
    }
}
