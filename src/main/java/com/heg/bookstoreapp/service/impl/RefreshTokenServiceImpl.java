package com.heg.bookstoreapp.service.impl;

import com.heg.bookstoreapp.exceptions.SendMailExceptions;
import com.heg.bookstoreapp.model.RefreshToken;
import com.heg.bookstoreapp.repo.RefreshTokenRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@Transactional
public class RefreshTokenServiceImpl {

    private final RefreshTokenRepo refreshTokenRepo;

    public RefreshTokenServiceImpl(RefreshTokenRepo refreshTokenRepo) {
        this.refreshTokenRepo = refreshTokenRepo;
    }

    public RefreshToken generateRefreshToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());

        return refreshTokenRepo.save(refreshToken);
    }

    void validateRefreshToken(String token) {
        refreshTokenRepo.findByToken(token)
                .orElseThrow(() -> new SendMailExceptions("Invalid refresh token"));
    }

    public void deleteRefreshToken(String token) {
        refreshTokenRepo.deleteByToken(token);
    }
}
