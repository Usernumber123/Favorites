package com.project.preferences.repository;

import com.project.preferences.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByLogin(String login);

    Optional<User> findByVerificationToken(UUID verificationToken);

    @Query("SELECT u FROM User u  JOIN FETCH u.favorite")
    List<User> findAllWithFavorite();
}
