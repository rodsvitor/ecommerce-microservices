package com.ecommerce.auth.application.usecase;

import com.ecommerce.auth.application.dto.LoginRequest;
import com.ecommerce.auth.application.dto.LoginResponse;
import com.ecommerce.auth.application.port.TokenProvider;
import com.ecommerce.auth.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginUseCase {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse execute(LoginRequest loginRequest) {

        var user = userRepository.findByEmail(loginRequest.login())
            .or(() -> userRepository.findByUsername(loginRequest.login()))
            .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = tokenProvider.generateToken(user.getUsername()) ;

        return new LoginResponse(token);

    }

}
