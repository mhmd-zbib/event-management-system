package dev.zbib.userservice.repository;

import dev.zbib.userservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
