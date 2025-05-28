package com.boy.pjtn.hello.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.boy.pjtn.hello.models.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {
  Optional<Product> findBySlug(String slug);
}
