package com.ecommerce.auth.integration;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static com.ecommerce.auth.integration.SharedPostgresContainer.POSTGRES_CONTAINER;

/**
 * Base class for all integration tests that need PostgreSQL.
 * Spins up a Testcontainers PostgreSQL database and injects
 * JDBC properties into Spring Boot automatically.
 * All integration tests should extend this class.
 */
@Testcontainers
@SpringBootTest
@Transactional
public abstract class BaseIntegrationTest {

  // Inject JDBC properties dynamically into Spring Boot
  @DynamicPropertySource
  static void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", POSTGRES_CONTAINER::getJdbcUrl);
    registry.add("spring.datasource.username", POSTGRES_CONTAINER::getUsername);
    registry.add("spring.datasource.password", POSTGRES_CONTAINER::getPassword);
  }

}
