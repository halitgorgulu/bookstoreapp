package com.heg.bookstoreapp.service.impl;

import com.heg.bookstoreapp.dto.AuthenticationResponse;
import com.heg.bookstoreapp.dto.LoginRequest;
import com.heg.bookstoreapp.dto.RegisterRequest;
import com.heg.bookstoreapp.exceptions.SendMailExceptions;
import com.heg.bookstoreapp.model.NotificationEmail;
import com.heg.bookstoreapp.model.RefreshTokenRequest;
import com.heg.bookstoreapp.model.User;
import com.heg.bookstoreapp.model.VerificationToken;
import com.heg.bookstoreapp.repo.UserRepo;
import com.heg.bookstoreapp.repo.VerificationTokenRepo;
import com.heg.bookstoreapp.security.JwtProvider;
import com.heg.bookstoreapp.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final VerificationTokenRepo verificationTokenRepo;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenServiceImpl refreshTokenService;

    public AuthServiceImpl(PasswordEncoder passwordEncoder, UserRepo userRepo, VerificationTokenRepo verificationTokenRepo, MailService mailService, AuthenticationManager authenticationManager, JwtProvider jwtProvider, RefreshTokenServiceImpl refreshTokenService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.verificationTokenRepo = verificationTokenRepo;
        this.mailService = mailService;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepo.save(user);

        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Please Activate Your Account",
                user.getEmail(),
                "Thank you for signing up to BookStoreManagement," +
                "please click on the below url to activate your account : " +
                "http://localhost:8081/api/auth/accountVerification/" + token));
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepo.save(verificationToken);

        return token;
    }


    @Override
    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepo.findByToken(token);
        verificationToken.orElseThrow(() -> new SendMailExceptions("Invalid Token"));
        fetchUserAndEnable(verificationToken.get());
    }

    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(loginRequest.getUsername())
                .build();
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithUsername(refreshTokenRequest.getUsername());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }

    private void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
         User user = userRepo.findByUsername(username).orElseThrow(() -> new SendMailExceptions("User not found with name: " + username));
         user.setEnabled(true);
         userRepo.save(user);
    }
}
