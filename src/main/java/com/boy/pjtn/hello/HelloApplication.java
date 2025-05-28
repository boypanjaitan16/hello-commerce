package com.boy.pjtn.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class HelloApplication {

  public static void main(String[] args) {
    Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
    dotenv.entries().forEach(e -> System.setProperty(e.getKey(), e.getValue()));
    System.setProperty("spring.profiles.active", dotenv.get("SPRING_PROFILES_ACTIVE", "dev"));

    SpringApplication.run(HelloApplication.class, args);
  }

}
