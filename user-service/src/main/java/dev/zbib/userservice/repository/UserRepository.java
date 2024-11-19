package dev.zbib.userservice.repository;

import dev.zbib.userservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUsersByProviderIds(Long[] providerIds);

}
