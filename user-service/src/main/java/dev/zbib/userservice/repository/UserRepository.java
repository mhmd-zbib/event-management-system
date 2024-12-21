package dev.zbib.userservice.repository;

import dev.zbib.userservice.dto.response.UserListResponse;
import dev.zbib.userservice.dto.response.UserResponse;
import dev.zbib.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<UserListResponse> findByIdIn(List<Long> ids);

    Optional<UserResponse> findUserResponseById(Long id);

    boolean existsByPhoneNumber(String phoneNumber);
}
