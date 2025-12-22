package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String productName;

    @NotBlank
    private String sku;

    @NotBlank
    private String category;

    public Product() {}

    /* =======================
       BUILDER SUPPORT (TESTS)
       ======================= */

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Product product = new Product();

        public Builder id(Long id) {
            product.setId(id);
            return this;
        }

        public Builder productName(String productName) {
            product.setProductName(productName);
            return this;
        }

        public Builder sku(String sku) {
            product.setSku(sku);
            return this;
        }

        public Builder category(String category) {
            product.setCategory(category);
            return this;
        }

        public Product build() {
            return product;
        }
    }

    /* =======================
       GETTERS & SETTERS
       ======================= */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
