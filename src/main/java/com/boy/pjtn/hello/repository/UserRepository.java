package com.boy.pjtn.hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.boy.pjtn.hello.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
