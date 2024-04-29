package com.example.myselectshop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_folder")
@Getter
@Setter
@NoArgsConstructor
public class ProductFolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productU_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Folder_id", nullable = false)
    private Folder folder;

    public ProductFolder(Product product, Folder folder) {
        this.product = product;
        this.folder = folder;
    }
}
