package com.example.retornosAPI.services;

import com.example.retornosAPI.models.Product;
import com.example.retornosAPI.models.ProductEntity;
import com.example.retornosAPI.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository repository;

    // Construtor para injeção de dependência do repositório
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product createProduct(Product product) {
        validateProduct(product); // Valida o produto antes de criar
        // Cria uma entidade de produto a partir do modelo recebido
        ProductEntity entity = new ProductEntity(
                null, // ID será gerado automaticamente
                product.name(),
                product.description(),
                product.price(),
                product.stockQuantity(),
                product.category()
        );
        // Salva a entidade no banco de dados
        ProductEntity savedEntity = repository.save(entity);
        // Mapeia a entidade salva de volta para o modelo de domínio
        return mapToProduct(savedEntity);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        validateProduct(updatedProduct); // Valida o produto atualizado
        ProductEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with ID " + id + " not found")); // Lança exceção se não encontrar

        // Atualiza os campos da entidade com os valores do produto atualizado
        existingEntity.setName(updatedProduct.name());
        existingEntity.setDescription(updatedProduct.description());
        existingEntity.setPrice(updatedProduct.price());
        existingEntity.setStockQuantity(updatedProduct.stockQuantity());
        existingEntity.setCategory(updatedProduct.category());

        // Salva a entidade atualizada no banco de dados
        ProductEntity savedEntity = repository.save(existingEntity);
        // Mapeia a entidade salva de volta para o modelo de domínio
        return mapToProduct(savedEntity);
    }

    public void deleteProduct(Long id) {
        // Verifica se o produto existe no banco de dados
        if (!repository.existsById(id)) {
            throw new RuntimeException("Product with ID " + id + " does not exist"); // Lança exceção se não existir
        }
        // Exclui o produto pelo ID
        repository.deleteById(id);
    }

    public List<Product> getAllProducts() {
        // Busca todas as entidades no banco de dados, mapeia para o modelo de domínio e retorna como lista
        return repository.findAll().stream()
                .map(this::mapToProduct) // Mapeia cada entidade para um modelo de domínio
                .collect(Collectors.toList()); // Coleta os resultados em uma lista
    }

    // Método para buscar um produto pelo ID
    public Product getProductById(Long id) {
        // Busca a entidade pelo ID e lança exceção se não encontrar
        ProductEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        // Mapeia a entidade encontrada para o modelo de domínio
        return mapToProduct(entity);
    }

    // Método privado para validar os dados de um produto
    private void validateProduct(Product product) {
        // Verifica se o nome do produto é nulo ou vazio
        if (product.name() == null || product.name().isBlank()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório.");
        }
        // Verifica se o nome do produto tem entre 3 e 100 caracteres
        if (product.name().length() < 3 || product.name().length() > 100) {
            throw new IllegalArgumentException("O nome do produto deve ter entre 3 e 100 caracteres.");
        }
        // Verifica se o preço do produto é nulo ou menor ou igual a 0
        if (product.price() == null || product.price() <= 0) {
            throw new IllegalArgumentException("O preço do produto deve ser maior que 0.");
        }
        // Verifica se a quantidade em estoque é nula ou menor que 0
        if (product.stockQuantity() == null || product.stockQuantity() < 0) {
            throw new IllegalArgumentException("A quantidade em estoque deve ser maior ou igual a 0.");
        }
        // Verifica se a categoria do produto é nula ou vazia
        if (product.category() == null || product.category().isBlank()) {
            throw new IllegalArgumentException("A categoria do produto é obrigatória.");
        }
        // Lista de categorias válidas
        List<String> validCategories = List.of("Eletrônicos", "Roupas", "Alimentos");
        // Verifica se a categoria do produto está na lista de categorias válidas
        if (!validCategories.contains(product.category())) {
            throw new IllegalArgumentException("Categoria inválida. As categorias válidas são: " + validCategories);
        }
    }

    // Método auxiliar para converter uma entidade de produto (usada no banco de dados)
    // para o modelo de domínio (usado na aplicação).
    private Product mapToProduct(ProductEntity entity) {
        // Cria e retorna um novo objeto `Product` com os dados da entidade.
        return new Product(
                entity.getId(), // ID do produto
                entity.getName(), // Nome do produto
                entity.getDescription(), // Descrição do produto
                entity.getPrice(), // Preço do produto
                entity.getStockQuantity(), // Quantidade em estoque
                entity.getCategory() // Categoria do produto
        );
    }
}