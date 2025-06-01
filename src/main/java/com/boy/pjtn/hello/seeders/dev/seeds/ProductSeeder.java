package com.boy.pjtn.hello.seeders.dev.seeds;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.boy.pjtn.hello.models.Product;
import com.boy.pjtn.hello.models.ProductCategory;
import com.boy.pjtn.hello.repositories.ProductCategoryRepository;
import com.boy.pjtn.hello.repositories.ProductRepository;
import lombok.AllArgsConstructor;

@Component
@Profile("dev")
@AllArgsConstructor
public class ProductSeeder {
  private final ProductRepository productRepository;
  private final ProductCategoryRepository productCategoryRepository;

  public void seed() {
    if (productRepository.count() == 0) {
      ProductCategory clothingCategory =
          productCategoryRepository.findBySlug("clothing").orElseThrow();
      ProductCategory pantiesCategory =
          productCategoryRepository.findBySlug("panties").orElseThrow();

      List<Product> products = List.of(
          Product.builder().name("T-Shirt").slug("t-shirt").category(clothingCategory)
              .description("A comfortable cotton t-shirt.").price(new BigDecimal("19.99"))
              .stock(100).imageUrl("https://example.com/images/tshirt.jpg").build(),
          Product.builder().name("Jeans").category(pantiesCategory).slug("jeans")
              .description("Stylish denim jeans.").price(new BigDecimal("49.99")).stock(50)
              .imageUrl("https://example.com/images/jeans.jpg").build());

      productRepository.saveAll(products);
    }
  }
}
