package com.example.myselectshop.controller;

import com.example.myselectshop.dto.ProductMyPriceRequestDto;
import com.example.myselectshop.dto.ProductRequestDto;
import com.example.myselectshop.dto.ProductResponseDto;
import com.example.myselectshop.security.UserDetailsImpl;
import com.example.myselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // 관심상품 등록
    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return productService.createdProduct(requestDto, userDetails.getUser());
    }

    // 관심상품 희망 최저가 등록
    @PutMapping("/product/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id, @RequestBody ProductMyPriceRequestDto requestDto) {
        return productService.updateProduct(id, requestDto);
    }

    // 관심상품 조회하기
    @GetMapping("/products")
    public List<ProductResponseDto> getProducts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return productService.getProducts(userDetails.getUser());
    }

    // 관리자 조회
    @GetMapping("/admin/products")
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts();
    }
}
