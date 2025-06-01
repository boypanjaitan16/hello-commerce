package com.boy.pjtn.hello.dtos.auth;

import java.time.LocalDateTime;
import java.util.UUID;
import com.boy.pjtn.hello.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
  private UUID id;
  private String email;
  private String fullName;
  private String phone;
  private String address;
  private String city;
  private String state;
  private String zipCode;
  private String country;
  private boolean emailVerified = false;
  private boolean isActive = true;
  private Role role;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
