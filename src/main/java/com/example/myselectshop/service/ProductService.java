package com.example.myselectshop.service;

import com.example.myselectshop.dto.ProductMyPriceRequestDto;
import com.example.myselectshop.dto.ProductRequestDto;
import com.example.myselectshop.dto.ProductResponseDto;
import com.example.myselectshop.entity.Product;
import com.example.myselectshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    // 관심상품 희망 최저가
    public static final int MIN_MY_PRICE = 100;

    // 관심상품 등록
    public ProductResponseDto createdProduct(ProductRequestDto requestDto) {
        Product product = productRepository.save(new Product(requestDto));

        return new ProductResponseDto(product);
    }

    // 관심상품 희망 최저가 등록
    public ProductResponseDto updateProduct(Long id, ProductMyPriceRequestDto requestDto) {
        int myprice = requestDto.getMyprice();

        if (myprice < MIN_MY_PRICE) {
            throw new IllegalArgumentException("유효하지 않은 관심 가격입니다. 최소 " + MIN_MY_PRICE + "원 이상으로 설정해 주세요.");
        }

        Product product = productRepository.findById(id).orElseThrow(() ->
                new NullPointerException("해당 상품을 찾을 수 없습니다.")
        );

        product.update(requestDto);

        return new ProductResponseDto(product);
    }
}
