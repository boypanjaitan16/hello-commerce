package com.boy.pjtn.hello.controllers.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.boy.pjtn.hello.dtos.auth.UserResponse;
import com.boy.pjtn.hello.services.UserService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
  private final UserService userService;

  @GetMapping("/me")
  public ResponseEntity<UserResponse> getCurrentUser(Authentication authentication) {
    UserResponse currentUser = userService.getCurrentUser(authentication);
    return ResponseEntity.ok(currentUser);
  }
}
