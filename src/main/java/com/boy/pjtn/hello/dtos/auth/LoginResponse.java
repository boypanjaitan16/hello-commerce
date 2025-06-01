package com.boy.pjtn.hello.dtos.auth;

import lombok.Data;

@Data
public class LoginResponse {
  private String token;
  private String tokenType = "Bearer";

  private UserResponse user;

  public LoginResponse(String token, UserResponse user) {
    this.token = token;
    this.user = user;
  }
}
