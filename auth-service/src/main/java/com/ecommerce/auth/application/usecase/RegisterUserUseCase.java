package com.ecommerce.auth.application.usecase;

import com.ecommerce.auth.application.port.TokenProvider;
import com.ecommerce.auth.domain.model.Role;
import com.ecommerce.auth.domain.model.User;
import com.ecommerce.auth.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RegisterUserUseCase {

    private UserRepository userRepository;
    private TokenProvider tokenProvider;
    private PasswordEncoder passwordEncoder;

    public RegisterUserResponse execute(RegisterUserRequest userRequest) {

        User user = new User(
            UUID.randomUUID(),
            userRequest.username,
            userRequest.email,
            passwordEncoder.encode(userRequest.password),
            Role.USER
        );

        user = userRepository.save(user);

        String token = tokenProvider.generateToken(userRequest.email);

        return new RegisterUserResponse(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            token);

    }

    public record RegisterUserRequest(String username, String email, String password) {}
    public record RegisterUserResponse(UUID id, String username, String email, String jwtToken) {}

}
