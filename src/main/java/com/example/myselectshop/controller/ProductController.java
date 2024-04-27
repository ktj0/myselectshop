package com.example.myselectshop.controller;

import com.example.myselectshop.dto.ProductMyPriceRequestDto;
import com.example.myselectshop.dto.ProductRequestDto;
import com.example.myselectshop.dto.ProductResponseDto;
import com.example.myselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // 관심상품 등록
    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto) {
        return productService.createdProduct(requestDto);
    }

    // 관심상품 희망 최저가 등록
    @PutMapping("/product/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id, @RequestBody ProductMyPriceRequestDto requestDto) {
        return productService.updateProduct(id, requestDto);
    }
}
