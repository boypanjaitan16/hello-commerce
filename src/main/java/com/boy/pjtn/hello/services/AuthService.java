package com.boy.pjtn.hello.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.boy.pjtn.hello.dtos.auth.LoginResponse;
import com.boy.pjtn.hello.dtos.auth.RegisterRequest;
import com.boy.pjtn.hello.dtos.auth.UserResponse;
import com.boy.pjtn.hello.exceptions.BadRequestException;
import com.boy.pjtn.hello.mappers.UserMapper;
import com.boy.pjtn.hello.models.Role;
import com.boy.pjtn.hello.models.User;
import com.boy.pjtn.hello.repositories.RoleRepository;
import com.boy.pjtn.hello.repositories.UserRepository;
import com.boy.pjtn.hello.utils.JwtUtil;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;
  private final UserMapper userMapper;

  public UserResponse registerUser(RegisterRequest request) {
    userRepository.findByEmail(request.getEmail()).ifPresent(user -> {
      throw new BadRequestException("Email is already registered");
    });

    Role userRole = roleRepository.findByName("CUSTOMER")
        .orElseThrow(() -> new BadRequestException("Default role CUSTOMER not found"));

    User newUser = User.builder().fullName(request.getFullName()).email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword())).role(userRole).build();

    userRepository.save(newUser);

    return userMapper.toUserResponse(newUser);
  }

  public LoginResponse login(String email, String password) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new BadRequestException("Invalid credentials"));

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new BadRequestException("Invalid credentials");
    }

    String token = jwtUtil.generateToken(user);
    UserResponse userResponse = userMapper.toUserResponse(user);

    return new LoginResponse(token, userResponse);
  }
}
