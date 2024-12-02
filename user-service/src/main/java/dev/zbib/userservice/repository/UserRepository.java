package dev.zbib.userservice.repository;

import dev.zbib.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUsersByIdIn(List<Long> ids);
}
