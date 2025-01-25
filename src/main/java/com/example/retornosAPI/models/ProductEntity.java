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

    /*
    * Nome (name):
    * É obrigatório (@NotBlank).
    * Deve ter entre 3 e 100 caracteres (@Size).
    */
    @Column (nullable = false, length =  100)
    @NotBlank(message = "Por favor, insira um nome")
    @Size(min = 3, max = 100, message = "O nome do produto deve ter de 3 a 100 caracteres")
    private String name;
    /*
    * Descrição (description):
    * É opcional.
    * Pode ter no máximo 500 caracteres (@Size).
    */
    @Column (length = 500)
    @Size(max = 500, message = "A Descrição do produto não pode passar de 500 caracteres.")
    private String description;

    /*
    * Preço (price):
    * É obrigatório.(nullable = false)
    * Deve ser maior que 0 (@Positive).
    */
    @Column(nullable = false)
    @Positive(message = "Por favor, insira um valor positivo.")
    private Double price;

    /*
    * Quantidade em Estoque (stockQuantity):
    * É obrigatório.(nullable = false) e @NotBlank
    * Deve ser um número inteiro maior ou igual a 0 (@Min).
    */
    @Column(nullable = false)
    @Min(value = 0, message = "O Estoque deve ser sempre Positivo.")
    private Double stockQuantity;

    /*
    * Categoria (category):
    * É obrigatório (@NotBlank).
    * Será validada posteriormente para garantir que pertence a uma lista de categorias válidas.
    */
    @Column(nullable = false)
    @NotBlank(message = "A categoria do produto é obrigatória")
    private String category;

    public ProductEntity() {
    }

    public ProductEntity(Long id, String name, Double price, Double stockQuantity, String category) {
        this.id = id;
        this.name = name;
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

    public @NotBlank(message = "Por favor, insira um nome") @Size(min = 3, max = 100, message = "O nome do produto deve ter de 3 a 100 caracteres") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Por favor, insira um nome") @Size(min = 3, max = 100, message = "O nome do produto deve ter de 3 a 100 caracteres") String name) {
        this.name = name;
    }

    public @Size(max = 500, message = "A Descrição do produto não pode passar de 500 caracteres.") String getDescription() {
        return description;
    }

    public void setDescription(@Size(max = 500, message = "A Descrição do produto não pode passar de 500 caracteres.") String description) {
        this.description = description;
    }

    public @Positive(message = "Por favor, insira um valor positivo.") Double getPrice() {
        return price;
    }

    public void setPrice(@Positive(message = "Por favor, insira um valor positivo.") Double price) {
        this.price = price;
    }

    public @Min(value = 0, message = "O Estoque deve ser sempre Positivo.") Double getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(@Min(value = 0, message = "O Estoque deve ser sempre Positivo.") Double stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public @NotBlank(message = "A categoria do produto é obrigatória") String getCategory() {
        return category;
    }

    public void setCategory(@NotBlank(message = "A categoria do produto é obrigatória") String category) {
        this.category = category;
    }
}