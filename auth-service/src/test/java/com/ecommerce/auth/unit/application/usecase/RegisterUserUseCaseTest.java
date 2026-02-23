package com.ecommerce.auth.unit.application.usecase;

import com.ecommerce.auth.application.port.TokenProvider;
import com.ecommerce.auth.application.usecase.RegisterUserUseCase;
import com.ecommerce.auth.domain.model.User;
import com.ecommerce.auth.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RegisterUserUseCaseTest {

  @Mock
  private UserRepository repository;

  @Mock
  private TokenProvider tokenProvider;

  @Mock
  private PasswordEncoder passwordEncoder;

  @InjectMocks
  private RegisterUserUseCase useCase;

  @BeforeEach
  void setup() {

    Mockito.when(repository.save(any(User.class)))
        .thenAnswer(invocation -> invocation.getArgument(0));
  }

  @Test
  void shouldRegisterUserSuccessfully() {

    RegisterUserUseCase.RegisterUserRequest registerUserRequest = new RegisterUserUseCase.RegisterUserRequest(
        "test_user",
        "test@email.com",
        "123456"
    );


    RegisterUserUseCase.RegisterUserResponse result = useCase.execute(registerUserRequest);

    assertNotNull(result);
    assertEquals("test_user", result.username());

    ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
    verify(repository).save(captor.capture());

    User savedUser = captor.getValue();
    assertNotNull(savedUser);
    assertEquals("test_user", savedUser.getUsername());
    assertEquals("test@email.com", savedUser.getEmail());

  }

}
