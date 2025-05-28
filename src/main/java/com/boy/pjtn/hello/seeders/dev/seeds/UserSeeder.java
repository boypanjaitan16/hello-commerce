package com.boy.pjtn.hello.seeders.dev.seeds;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.boy.pjtn.hello.model.User;
import com.boy.pjtn.hello.repository.UserRepository;

@Component
@Profile("dev")
public class UserSeeder {
  private final UserRepository userRepository;

  public UserSeeder(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void seed() {
    if (userRepository.count() == 0) {
      userRepository.save(new User("DEV-Alice", "alice@example.com"));
      userRepository.save(new User("DEV-Bob", "bob@example.com"));
    }
  }
}
