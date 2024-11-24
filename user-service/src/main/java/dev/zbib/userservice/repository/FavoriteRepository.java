package dev.zbib.userservice.repository;

import dev.zbib.userservice.model.entity.Favorite;
import org.springframework.data.repository.CrudRepository;

public interface FavoriteRepository extends CrudRepository<Favorite, Long> {
}
