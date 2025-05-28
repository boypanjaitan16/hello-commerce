package com.boy.pjtn.hello.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.boy.pjtn.hello.model.User;
import com.boy.pjtn.hello.repository.UserRepository;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public Optional<User> getUserById(Long id) {
    return userRepository.findById(id);
  }
}
