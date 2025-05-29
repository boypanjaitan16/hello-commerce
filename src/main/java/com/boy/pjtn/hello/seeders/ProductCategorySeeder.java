package com.boy.pjtn.hello.seeders;

import java.util.List;
import org.springframework.stereotype.Component;
import com.boy.pjtn.hello.models.ProductCategory;
import com.boy.pjtn.hello.repositories.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductCategorySeeder {
  private final ProductCategoryRepository productCategoryRepository;

  public void seed() {
    if (productCategoryRepository.count() == 0) {
      List<ProductCategory> categories = List.of(
          ProductCategory.builder().name("Clothing").slug("clothing")
              .description("All clothes, short sleve, long sleve").build(),
          ProductCategory.builder().name("Panties").slug("panties")
              .description("Like shorts, jeans, cargo").build());
      productCategoryRepository.saveAll(categories);
    }
  }
}
