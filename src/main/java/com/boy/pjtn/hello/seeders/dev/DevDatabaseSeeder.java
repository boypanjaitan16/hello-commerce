package com.boy.pjtn.hello.seeders.dev;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.boy.pjtn.hello.seeders.ProductCategorySeeder;
import com.boy.pjtn.hello.seeders.RoleSeeder;
import com.boy.pjtn.hello.seeders.dev.seeds.ProductSeeder;
import com.boy.pjtn.hello.seeders.dev.seeds.UserSeeder;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Component
@Profile("dev")
@AllArgsConstructor
public class DevDatabaseSeeder {
  private final UserSeeder userSeeder;
  private final RoleSeeder roleSeeder;
  private final ProductSeeder productSeeder;
  private final ProductCategorySeeder productCategorySeeder;

  @PostConstruct
  public void seed() {
    roleSeeder.seed();
    productCategorySeeder.seed();
    userSeeder.seed();
    productSeeder.seed();

  }

}
