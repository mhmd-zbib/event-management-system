package dev.zbib.profileservice.repository;

import dev.zbib.profileservice.dto.response.ProfileListResponse;
import dev.zbib.profileservice.dto.response.ProfileResponse;
import dev.zbib.profileservice.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    List<ProfileListResponse> findByIdIn(List<Long> ids);

    Optional<ProfileResponse> findUserResponseById(Long id);

    boolean existsByPhoneNumber(String phoneNumber);
}
