package com.ecommerce.auth.integration;

import org.testcontainers.containers.PostgreSQLContainer;

public class SharedPostgresContainer {

  // Postgres container for tests
  public static final PostgreSQLContainer<?> POSTGRES_CONTAINER;

  // Will be started automatically by Testcontainers
  static {

    POSTGRES_CONTAINER = new PostgreSQLContainer<>("postgres:15")
        .withDatabaseName("test_db")
        .withUsername("test_user")
        .withPassword("test_pass");

    POSTGRES_CONTAINER.start();

  }

}
