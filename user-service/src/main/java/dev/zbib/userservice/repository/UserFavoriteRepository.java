package dev.zbib.userservice.repository;

import dev.zbib.userservice.entity.UserFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserFavoriteRepository extends JpaRepository<UserFavorite, Long> {
    List<UserFavorite> findByUserId(Long userId);
    void deleteByUserIdAndProviderId(Long userId, Long providerId);
} 