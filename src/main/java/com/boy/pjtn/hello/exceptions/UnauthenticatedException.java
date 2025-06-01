package com.boy.pjtn.hello.exceptions;

public class UnauthenticatedException extends RuntimeException {
  public UnauthenticatedException(String message) {
    super(message);
  }
}
