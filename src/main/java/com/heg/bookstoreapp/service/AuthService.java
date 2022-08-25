package com.heg.bookstoreapp.service;

import com.heg.bookstoreapp.dto.AuthenticationResponse;
import com.heg.bookstoreapp.dto.LoginRequest;
import com.heg.bookstoreapp.dto.RegisterRequest;
import com.heg.bookstoreapp.model.RefreshTokenRequest;

public interface AuthService {

    void signup(RegisterRequest registerRequest);

    void verifyAccount(String token);

    AuthenticationResponse login(LoginRequest loginRequest);

    AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
