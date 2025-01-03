package dev.zbib.profileservice.repository;

import dev.zbib.profileservice.dto.ProfileListResponse;
import dev.zbib.profileservice.dto.ProfileResponse;
import dev.zbib.profileservice.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {

    List<ProfileListResponse> findByIdIn(List<String> ids);

    Optional<ProfileResponse> findProfileResponseById(String id);
}
