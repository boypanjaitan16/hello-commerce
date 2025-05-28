package com.boy.pjtn.hello.seeders;

import java.util.List;
import org.springframework.stereotype.Component;
import com.boy.pjtn.hello.models.Role;
import com.boy.pjtn.hello.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoleSeeder {

  private final RoleRepository roleRepository;

  public void seed() {
    if (roleRepository.count() == 0) {
      List<Role> roles = List.of(Role.builder().name("ADMIN").description("Admin Role").build(),
          Role.builder().name("CUSTOMER").description("Customer Role").build());
      roleRepository.saveAll(roles);
    }
  }
}
