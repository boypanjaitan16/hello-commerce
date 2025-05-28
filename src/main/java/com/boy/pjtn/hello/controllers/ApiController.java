package com.boy.pjtn.hello.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.boy.pjtn.hello.models.User;
import com.boy.pjtn.hello.services.UserService;

@RestController
@RequestMapping("/api")
public class ApiController {
  private final UserService userService;

  public ApiController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  public List<User> getUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    return userService.getUserById(id).map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
}
