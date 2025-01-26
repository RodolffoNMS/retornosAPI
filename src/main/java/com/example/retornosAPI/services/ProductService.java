package com.example.retornosAPI.services;

import com.example.retornosAPI.exception.ProductNotFoundException;
import com.example.retornosAPI.models.Product;
import com.example.retornosAPI.models.ProductEntity;
import com.example.retornosAPI.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product createProduct(Product product) {
        ProductEntity entity = new ProductEntity(null, product.name(), product.description(), product.price(), product.stockQuantity(), product.category());
        ProductEntity savedEntity = repository.save(entity);
        return new Product( savedEntity.getId(), savedEntity.getName(), savedEntity.getDescription(), savedEntity.getPrice(), savedEntity.getStockQuantity(), savedEntity.getCategory());
    }

    public Product getProductById(Long id) {
        ProductEntity entity = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
        return new Product( entity.getId(), entity.getName(), entity.getDescription(), entity.getPrice(), entity.getStockQuantity(), entity.getCategory());
    }

    public List<Product> getAllProducts() {
        return repository.findAll().stream()
                .map(entity -> new Product( entity.getId(), entity.getName(), entity.getDescription(), entity.getPrice(), entity.getStockQuantity(), entity.getCategory()))
                .collect(Collectors.toList());
    }

    public void deleteProduct(Long id) {
        if (!repository.existsById(id)) {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }
        repository.deleteById(id);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        ProductEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));

        existingEntity.setName(updatedProduct.name());
        existingEntity.setDescription(updatedProduct.description());
        existingEntity.setPrice(updatedProduct.price());
        existingEntity.setStockQuantity(updatedProduct.stockQuantity());
        existingEntity.setCategory(updatedProduct.category());

        ProductEntity savedEntity = repository.save(existingEntity);

        return new Product( savedEntity.getId(), savedEntity.getName(), savedEntity.getDescription(), savedEntity.getPrice(), savedEntity.getStockQuantity(), savedEntity.getCategory());
    }

    public List<Product> getProductsByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode ser vazio.");
        }

        List<ProductEntity> entities = repository.findByNameContainingIgnoreCase(name);
        if (entities.isEmpty()) {
            System.out.println("Nenhum produto encontrado com o nome: " + name);
        } else {
            System.out.println("Produtos encontrados com o nome '" + name + "': " + entities.size());
        }
        return entities.stream()
                .map(entity -> new Product(entity.getId(), entity.getName(), entity.getDescription(), entity.getPrice(), entity.getStockQuantity(), entity.getCategory()))
                .collect(Collectors.toList());
    }
}