package dev.zbib.userservice.repository;

import dev.zbib.userservice.entity.Favorite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    @Query("SELECT f.providerId FROM Favorite f WHERE f.user.id = :userId")
    Page<Long> findProviderIdsByUserId(
            Long userId,
            Pageable pageable);
}
