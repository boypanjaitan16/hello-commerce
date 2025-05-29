package com.boy.pjtn.hello.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.boy.pjtn.hello.models.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, UUID> {
  Optional<ProductCategory> findBySlug(String slug);
}
