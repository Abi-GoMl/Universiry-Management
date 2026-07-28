package com.example.studentcrud.Controller;

import com.example.studentcrud.Controller.dto.AuthResponse;
import com.example.studentcrud.Controller.dto.LoginRequest;
import com.example.studentcrud.Entity.RefreshToken;
import com.example.studentcrud.Exception.TokenRefreshException;
import com.example.studentcrud.Security.JwtUtil;
import com.example.studentcrud.Service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        UserDetails ud = userDetailsService.loadUserByUsername(request.getUsername());
        String accessToken = jwtUtil.generateAccessToken(ud.getUsername());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(ud.getUsername());
        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken.getToken()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody Map<String, String> body) {
        String requestToken = body.get("refreshToken");
        if (requestToken == null) throw new TokenRefreshException("Refresh token is required");
        RefreshToken rt = refreshTokenService.findByToken(requestToken)
                .orElseThrow(() -> new TokenRefreshException("Refresh token not found"));
        refreshTokenService.verifyExpiration(rt);
        // rotate refresh token
        refreshTokenService.deleteByUsername(rt.getUsername());
        RefreshToken newRt = refreshTokenService.createRefreshToken(rt.getUsername());
        String newAccess = jwtUtil.generateAccessToken(rt.getUsername());
        return ResponseEntity.ok(new AuthResponse(newAccess, newRt.getToken()));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        if (username != null) {
            refreshTokenService.deleteByUsername(username);
        }
        return ResponseEntity.ok(Map.of("loggedOut", true));
    }
}
