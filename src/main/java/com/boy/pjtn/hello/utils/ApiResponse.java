package com.boy.pjtn.hello.utils;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> {
  private int status;
  private String error;
  private T data;
  private LocalDateTime timestamp;
}
