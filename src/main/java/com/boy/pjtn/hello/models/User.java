package com.boy.pjtn.hello.models;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tbl_users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false, unique = true, length = 100)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String fullName;

  private String phone;

  private String address;

  private String city;

  private String state;

  private String zipCode;

  private String country;

  @Builder.Default
  @Column(nullable = false)
  private boolean emailVerified = false;

  @Builder.Default
  @Column(nullable = false)
  private boolean isActive = true;

  @ManyToOne
  @JoinColumn(name = "role_id")
  private Role role;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @PrePersist
  protected void onCreate() {
    this.createdAt = this.updatedAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = LocalDateTime.now();
  }
}
