package com.boy.pjtn.hello.seeders.prod.seeds;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.boy.pjtn.hello.model.User;
import com.boy.pjtn.hello.repository.UserRepository;

@Component
@Profile("prod")
public class UserSeeder {
  private final UserRepository userRepository;

  public UserSeeder(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void seed() {
    if (userRepository.count() == 0) {
      userRepository.save(new User("Alice", "alice@example.com"));
      userRepository.save(new User("Bob", "bob@example.com"));
    }
  }
}
