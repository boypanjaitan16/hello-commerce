package com.boy.pjtn.hello.seeders.prod;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.boy.pjtn.hello.seeders.prod.seeds.UserSeeder;
import lombok.RequiredArgsConstructor;

@Component
@Profile("prod")
@RequiredArgsConstructor
public class ProdDatabaseSeeder implements CommandLineRunner {
  private final UserSeeder userSeeder;

  @Override
  public void run(String... args) throws Exception {
    userSeeder.seed();
  }
}
