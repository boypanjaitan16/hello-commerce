package com.boy.pjtn.hello.seeders.prod.seeds;

import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.boy.pjtn.hello.models.Role;
import com.boy.pjtn.hello.models.User;
import com.boy.pjtn.hello.repositories.RoleRepository;
import com.boy.pjtn.hello.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@Profile("prod")
@RequiredArgsConstructor
public class UserSeeder {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  public void seed() {
    if (userRepository.count() == 0) {
      Role adminRole = roleRepository.findByName("ADMIN").orElseThrow();

      List<User> users = List.of(User.builder().fullName("Admin User").email("admin@example.com")
          .password(passwordEncoder.encode("password")).role(adminRole).build());
      userRepository.saveAll(users);
    }
  }
}
