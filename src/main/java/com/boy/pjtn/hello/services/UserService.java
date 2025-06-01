package com.boy.pjtn.hello.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.boy.pjtn.hello.dtos.auth.UserResponse;
import com.boy.pjtn.hello.mappers.UserMapper;
import com.boy.pjtn.hello.models.User;
import com.boy.pjtn.hello.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public Optional<User> getUserById(UUID id) {
    return userRepository.findById(id);
  }

  public UserResponse getCurrentUser(Authentication authentication) {
    String email = authentication.getName();
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    return userMapper.toUserResponse(user);
  }
}
