package com.boy.pjtn.hello.models;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_roles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {

  @Id
  @GeneratedValue
  private UUID id;

  @Column(nullable = false, unique = true)
  private String name;

  private String description;

  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLE_" + name));
  }
}
