package com.boy.pjtn.hello.controllers.api;

import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.boy.pjtn.hello.models.Product;
import com.boy.pjtn.hello.services.ProductService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
  private final ProductService productService;

  @GetMapping("")
  public ResponseEntity<List<Product>> allProducts() {
    return ResponseEntity.ok(productService.getAllProducts());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable UUID id) {
    return ResponseEntity.ok(productService.getProductById(id));
  }

  @GetMapping("/slug/{slug}")
  public ResponseEntity<Product> getProductBySlug(@PathVariable String slug) {
    return ResponseEntity.ok(productService.getProductBySlug(slug));
  }
}
