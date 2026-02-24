package com.vieri.stockcontrol.integration;

import com.vieri.stockcontrol.domain.entity.Product;
import com.vieri.stockcontrol.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ProductIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldPersistAndFindProductInRealDatabase() {

        Product product = Product.builder()
                .code("INT-001")
                .name("Integration Product")
                .price(new BigDecimal("999.99"))
                .build();

        Product saved = productRepository.save(product);

        Optional<Product> found = productRepository.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getCode()).isEqualTo("INT-001");
        assertThat(found.get().getPrice()).isEqualByComparingTo("999.99");
    }
}