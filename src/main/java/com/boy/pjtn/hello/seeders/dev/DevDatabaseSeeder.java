package com.boy.pjtn.hello.seeders.dev;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.boy.pjtn.hello.seeders.dev.seeds.UserSeeder;

@Component
@Profile("dev")
public class DevDatabaseSeeder implements CommandLineRunner {
  private final UserSeeder userSeeder;

  public DevDatabaseSeeder(UserSeeder userSeeder) {
    this.userSeeder = userSeeder;
  }

  @Override
  public void run(String... args) throws Exception {
    userSeeder.seed();
  }

}
