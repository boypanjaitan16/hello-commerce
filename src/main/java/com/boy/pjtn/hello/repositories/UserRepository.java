package com.boy.pjtn.hello.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.boy.pjtn.hello.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
