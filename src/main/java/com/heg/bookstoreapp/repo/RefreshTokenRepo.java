package com.heg.bookstoreapp.repo;

import com.heg.bookstoreapp.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);
}
