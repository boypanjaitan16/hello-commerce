package com.boy.pjtn.hello.services;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.boy.pjtn.hello.exceptions.DataNotFoundException;
import com.boy.pjtn.hello.models.Product;
import com.boy.pjtn.hello.repositories.ProductRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Product getProductBySlug(String slug) {
    return productRepository.findBySlug(slug)
        .orElseThrow(() -> new DataNotFoundException("Product with slug '" + slug + "' not found"));
  }

  public Product getProductById(UUID id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new DataNotFoundException("Product with ID '" + id + "' not found"));
  }
}
