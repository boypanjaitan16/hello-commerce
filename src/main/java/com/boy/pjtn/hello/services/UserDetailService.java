package com.boy.pjtn.hello.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.boy.pjtn.hello.exceptions.UnauthenticatedException;
import com.boy.pjtn.hello.models.User;
import com.boy.pjtn.hello.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class UserDetailService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UnauthenticatedException("User not found with email: " + email));

    return new org.springframework.security.core.userdetails.User(user.getEmail(),
        user.getPassword(), true, // enabled
        true, // accountNonExpired
        true, // credentialsNonExpired
        true, // accountNonLocked
        user.getRole().getAuthorities() // convert role to granted authorities
    );
  }
}
