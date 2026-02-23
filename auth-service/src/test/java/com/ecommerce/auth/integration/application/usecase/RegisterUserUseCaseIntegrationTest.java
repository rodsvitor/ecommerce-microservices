package com.ecommerce.auth.integration.application.usecase;

import com.ecommerce.auth.application.usecase.RegisterUserUseCase;
import com.ecommerce.auth.integration.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RegisterUserUseCaseIntegrationTest extends BaseIntegrationTest {

  @Autowired
  private RegisterUserUseCase useCase;

  @Test
  void shouldSaveUser() {

    RegisterUserUseCase.RegisterUserRequest registerUserRequest = new RegisterUserUseCase.RegisterUserRequest(
        "testUser",
        "test@email.com",
        "123456"
    );

    RegisterUserUseCase.RegisterUserResponse user = useCase.execute(registerUserRequest);

    assertNotNull(user.id());
    assertEquals("testUser", user.username());

  }

}