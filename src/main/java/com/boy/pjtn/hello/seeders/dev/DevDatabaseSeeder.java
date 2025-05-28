package com.boy.pjtn.hello.seeders.dev;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.boy.pjtn.hello.seeders.RoleSeeder;
import com.boy.pjtn.hello.seeders.dev.seeds.ProductSeeder;
import com.boy.pjtn.hello.seeders.dev.seeds.UserSeeder;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class DevDatabaseSeeder {
  private final UserSeeder userSeeder;
  private final RoleSeeder roleSeeder;
  private final ProductSeeder productSeeder;

  @PostConstruct
  public void seed() {
    roleSeeder.seed();
    userSeeder.seed();
    productSeeder.seed();
  }

}
