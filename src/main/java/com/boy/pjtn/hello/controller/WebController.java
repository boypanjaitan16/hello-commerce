package com.boy.pjtn.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("title", "Welcome to Spring Boot!");
    model.addAttribute("message", "Yuhoo! This is a simple web application using Spring Boot.");

    return "index";
  }
}
