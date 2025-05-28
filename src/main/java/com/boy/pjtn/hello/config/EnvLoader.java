package com.boy.pjtn.hello.config;

import org.springframework.context.annotation.Configuration;
import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class EnvLoader {
  public static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
}
