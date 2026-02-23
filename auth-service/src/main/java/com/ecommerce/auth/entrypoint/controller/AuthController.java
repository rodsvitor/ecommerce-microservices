package com.ecommerce.auth.entrypoint.controller;

import com.ecommerce.auth.application.dto.LoginRequest;
import com.ecommerce.auth.application.dto.LoginResponse;
import com.ecommerce.auth.application.usecase.LoginUseCase;
import com.ecommerce.auth.application.usecase.RegisterUserUseCase;
import com.ecommerce.auth.application.usecase.RegisterUserUseCase.RegisterUserRequest;
import com.ecommerce.auth.application.usecase.RegisterUserUseCase.RegisterUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final LoginUseCase loginUseCase;
  private final RegisterUserUseCase registerUserUseCase;

  @PostMapping("/login")
  public LoginResponse login(@RequestBody LoginRequest request) {
    return loginUseCase.execute(request);
  }

  @PostMapping("/register")
  public RegisterUserResponse register(@RequestBody RegisterUserRequest request) {
    return registerUserUseCase.execute(request);
  }

}
