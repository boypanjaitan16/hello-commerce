package com.boy.pjtn.hello.seeders.dev.seeds;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.boy.pjtn.hello.models.Product;
import com.boy.pjtn.hello.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class ProductSeeder {
  private final ProductRepository productRepository;

  public void seed() {
    if (productRepository.count() == 0) {
      List<Product> products = List.of(
          Product.builder().name("T-Shirt").slug("t-shirt")
              .description("A comfortable cotton t-shirt.").price(new BigDecimal("19.99"))
              .stock(100).imageUrl("https://example.com/images/tshirt.jpg").build(),
          Product.builder().name("Jeans").slug("jeans").description("Stylish denim jeans.")
              .price(new BigDecimal("49.99")).stock(50)
              .imageUrl("https://example.com/images/jeans.jpg").build());

      productRepository.saveAll(products);
    }
  }
}
