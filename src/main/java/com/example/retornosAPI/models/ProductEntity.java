package com.example.retornosAPI.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Por favor, insira um nome")
    @Size(min = 3, max = 100, message = "O nome do produto deve ter de 3 a 100 caracteres")
    private String name;

    @Column(length = 500)
    @Size(max = 500, message = "A descrição do produto não pode passar de 500 caracteres.")
    private String description;

    @Column(nullable = false)
    @Positive(message = "Por favor, insira um valor positivo.")
    private Double price;

    @Column(nullable = false)
    @Min(value = 0, message = "O estoque deve ser sempre positivo.")
    private Double stockQuantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    public ProductEntity() {
    }

    public ProductEntity(Long id, String name, String description, Double price, Double stockQuantity, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Double stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}