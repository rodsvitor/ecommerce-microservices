package com.ecommerce.auth.application.port;

public interface TokenProvider {

  String generateToken(String username);

}
