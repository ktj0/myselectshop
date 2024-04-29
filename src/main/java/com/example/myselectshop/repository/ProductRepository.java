package com.example.myselectshop.repository;

import com.example.myselectshop.entity.Product;
import com.example.myselectshop.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByUser(User user);
    Page<Product> findAllByUser(User user, Pageable pageable);
}
