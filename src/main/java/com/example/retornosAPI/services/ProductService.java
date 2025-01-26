package com.example.retornosAPI.services;

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
        validateProduct(product);
        ProductEntity entity = new ProductEntity(
                null,
                product.name(),
                product.description(),
                product.price(),
                product.stockQuantity(),
                product.category()
        );
        ProductEntity savedEntity = repository.save(entity);
        return mapToProduct(savedEntity);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        validateProduct(updatedProduct);
        ProductEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with ID " + id + " not found"));

        existingEntity.setName(updatedProduct.name());
        existingEntity.setDescription(updatedProduct.description());
        existingEntity.setPrice(updatedProduct.price());
        existingEntity.setStockQuantity(updatedProduct.stockQuantity());
        existingEntity.setCategory(updatedProduct.category());

        ProductEntity savedEntity = repository.save(existingEntity);
        return mapToProduct(savedEntity);
    }

    public void deleteProduct(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Product with ID " + id + " does not exist");
        }
        repository.deleteById(id);
    }

    public List<Product> getAllProducts() {
        return repository.findAll().stream()
                .map(this::mapToProduct)
                .collect(Collectors.toList()); 
    }

    public Product getProductById(Long id) {
        ProductEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return mapToProduct(entity);
    }

    private void validateProduct(Product product) {

        if (product.name() == null || product.name().isBlank()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório.");
        }

        if (product.name().length() < 3 || product.name().length() > 100) {
            throw new IllegalArgumentException("O nome do produto deve ter entre 3 e 100 caracteres.");
        }

        if (product.price() == null || product.price() <= 0) {
            throw new IllegalArgumentException("O preço do produto deve ser maior que 0.");
        }

        if (product.stockQuantity() == null || product.stockQuantity() < 0) {
            throw new IllegalArgumentException("A quantidade em estoque deve ser maior ou igual a 0.");
        }

        if (product.category() == null || product.category().isBlank()) {
            throw new IllegalArgumentException("A categoria do produto é obrigatória.");
        }

        List<String> validCategories = List.of("Eletrônicos", "Roupas", "Alimentos");
        if (!validCategories.contains(product.category())) {
            throw new IllegalArgumentException("Categoria inválida. As categorias válidas são: " + validCategories);
        }
    }

    private Product mapToProduct(ProductEntity entity) {
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getStockQuantity(),
                entity.getCategory()
        );
    }
}