package com.growmighty.lectures.firstday.product;

import com.growmighty.lectures.firstday.product.application.ProductService;
import com.growmighty.lectures.firstday.product.application.dto.ProductInfo;
import com.growmighty.lectures.firstday.product.application.dto.RegisterProductCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("!test")
@RequiredArgsConstructor
public class ProductDataInitializer implements CommandLineRunner {
    private final ProductService productService;

    @Override
    public void run(String... args) {
        ProductInfo table = productService.register(
            new RegisterProductCommand(1L, "원목 4인용 식탁", BigDecimal.valueOf(179000), 100, "테스트용 상품"));
        ProductInfo chair = productService.register(
            new RegisterProductCommand(1L, "원목 의자", BigDecimal.valueOf(49000), 100, "테스트용 상품"));

        System.out.printf("[seed] product-service 준비 완료. 상품 id=%d, %d%n", table.id(), chair.id());
    }
}
