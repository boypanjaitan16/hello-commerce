package com.boy.pjtn.hello.controllers.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.boy.pjtn.hello.dtos.auth.LoginRequest;
import com.boy.pjtn.hello.dtos.auth.LoginResponse;
import com.boy.pjtn.hello.dtos.auth.RegisterRequest;
import com.boy.pjtn.hello.dtos.auth.UserResponse;
import com.boy.pjtn.hello.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
    return ResponseEntity.ok(authService.registerUser(request));
  }

  @PostMapping("/signin")
  public ResponseEntity<LoginResponse> signIn(@RequestBody @Valid LoginRequest request) {
    LoginResponse loginResponse = authService.login(request.getEmail(), request.getPassword());
    return ResponseEntity.ok(loginResponse);
  }
}
