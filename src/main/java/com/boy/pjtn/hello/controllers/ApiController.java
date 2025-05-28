package com.boy.pjtn.hello.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ApiController {


  @GetMapping("/")
  public String index() {
    return "Welcome to the API!";
  }
}
