package com.boy.pjtn.hello.exceptions;

public class DataNotFoundException extends RuntimeException {
  public DataNotFoundException(String message) {
    super(message);
  }
}
