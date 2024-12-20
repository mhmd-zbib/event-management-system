package dev.zbib.userservice.service;

import dev.zbib.userservice.dto.request.CreateUserRequest;
import dev.zbib.userservice.exception.PhoneNumberAlreadyExistsException;
import dev.zbib.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserValidationService {

    private final UserRepository userRepository;

    public void validateUserCreation(CreateUserRequest req) {
        validatePhoneNumber(req.getPhoneNumber());
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new PhoneNumberAlreadyExistsException(phoneNumber);
        }
    }
}
