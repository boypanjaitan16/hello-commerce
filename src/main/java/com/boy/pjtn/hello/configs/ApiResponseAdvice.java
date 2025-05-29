package com.boy.pjtn.hello.configs;

import java.time.LocalDateTime;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import com.boy.pjtn.hello.utils.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ApiResponseAdvice implements ResponseBodyAdvice<Object> {

  @Override
  public boolean supports(@NonNull MethodParameter returnType,
      @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
    // Wrap all JSON responses
    return true;
  }

  @Override
  @Nullable
  public Object beforeBodyWrite(@Nullable Object body, @NonNull MethodParameter returnType,
      @NonNull MediaType selectedContentType,
      @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
      @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {
    // Skip non-JSON responses
    if (!selectedContentType.includes(MediaType.APPLICATION_JSON)) {
      return body;
    }

    // Prevent double-wrapping
    if (body instanceof ApiResponse) {
      return body;
    }

    HttpServletResponse servletResponse =
        ((ServletServerHttpResponse) response).getServletResponse();
    return ApiResponse.builder().status(servletResponse.getStatus()).error(null).data(body)
        .timestamp(LocalDateTime.now()).build();
  }
}
