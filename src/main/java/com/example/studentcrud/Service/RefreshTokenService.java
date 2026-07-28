package com.example.studentcrud.Service;

import com.example.studentcrud.Entity.RefreshToken;
import com.example.studentcrud.Exception.ResourceNotFoundException;
import com.example.studentcrud.Repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final long refreshDurationMs;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository,
                               @Value("${JWT_REFRESH_EXP:604800000}") long refreshDurationMs) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.refreshDurationMs = refreshDurationMs;
    }

    public RefreshToken createRefreshToken(String username) {
        RefreshToken rt = new RefreshToken();
        rt.setToken(UUID.randomUUID().toString());
        rt.setUsername(username);
        rt.setExpiryDate(Instant.now().plusMillis(refreshDurationMs));
        return refreshTokenRepository.save(rt);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(token);
            throw new ResourceNotFoundException("Refresh token was expired. Please login again.");
        }
        return token;
    }

    public int deleteByUsername(String username) {
        return refreshTokenRepository.deleteByUsername(username);
    }
}
