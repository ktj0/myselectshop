package com.example.myselectshop.controller;

import com.example.myselectshop.dto.ProductMyPriceRequestDto;
import com.example.myselectshop.dto.ProductRequestDto;
import com.example.myselectshop.dto.ProductResponseDto;
import com.example.myselectshop.security.UserDetailsImpl;
import com.example.myselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public Page<ProductResponseDto> getProducts(@RequestParam("page") int page,
                                                @RequestParam("size") int size,
                                                @RequestParam("sortBy") String sortBy,
                                                @RequestParam("isAsc") boolean isAsc,
                                                @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return productService.getProducts(userDetails.getUser(), page-1, size, sortBy, isAsc);
    }

    // 관리자 조회
    @GetMapping("/admin/products")
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts();
    }
}
