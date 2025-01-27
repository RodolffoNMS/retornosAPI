# 📦 Retornos API

Esta é uma API para gerenciar produtos, permitindo criar, listar, atualizar e deletar produtos. A aplicação foi desenvolvida utilizando Spring Boot.

## 🛠️ Pré-requisitos

- ☕ Java 17 ou superior
- 📦 Maven
- 🗄️ Banco de dados configurado (ex.: MySQL, PostgreSQL, etc.)

## ⚙️ Configuração do Banco de Dados

Certifique-se de configurar o banco de dados no arquivo `application.properties` ou `application.yml` antes de executar a aplicação.

Exemplo de configuração para MySQL:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/retornos_api
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
````

## 🚀 Executando a Aplicação
1.  Clone o repositório:
````
git clone https://github.com/seu-usuario/retornos-api.git
cd retornos-api
````
2. Compile e execute a aplicação:
````
mvn spring-boot:run
````
3. A API estará disponível em: http://localhost:8080/api/products

## 🌐 Endpoints da API
### 1️⃣ Criar Produto
#### POST /api/products
#### Body (JSON):
````
{
"name": "Produto Exemplo",
"description": "Descrição do produto",
"price": 100.0,
"stockQuantity": 10,
"category": "Eletrônicos"
}
````
### Respostas:

✅ 200 OK: Produto criado com sucesso.

❌ 400 Bad Request: Erro de validação nos campos.

### 2️⃣ Listar Todos os Produtos
> GET /api/products

### Respostas:
✅ 200 OK: Retorna uma lista de produtos.

### 3️⃣ Buscar Produto por ID
> GET /api/products/{id}

### Parâmetros:

> id (Long): ID do produto.
### Respostas:

✅ 200 OK: Retorna o produto correspondente.

❌ 404 Not Found: Produto não encontrado.

### 4️⃣ Atualizar Produto
> PUT /api/products/{id} 

Parâmetros:

id (Long): ID do produto.

Body (JSON):
````
{
"name": "Produto Atualizado",
"description": "Nova descrição",
"price": 150.0,
"stockQuantity": 5,
"category": "Roupas"
}
````
### Respostas:
✅ 200 OK: Produto atualizado com sucesso.

❌ 400 Bad Request: Erro de validação nos campos.

❌ 404 Not Found: Produto não encontrado.

### 5️⃣ Deletar Produto

> DELETE /api/products/{id}

Parâmetros:

id (Long): ID do produto.

### Respostas:

✅ 204 No Content: Produto deletado com sucesso.

❌ 404 Not Found: Produto não encontrado.


## 🛡️ Validações

**Nome**: Obrigatório, entre 3 e 100 caracteres.

**Descrição**: *Opcional*, até 500 caracteres.

**Preço**: *Obrigatório*, maior que 0
.
**Quantidade em Estoque**: Obrigatório, maior ou igual a 0.

**Categoria: Obrigatória**, deve ser uma das seguintes:
````
Eletrônicos
Roupas
Alimentos
````

## ⚠️ Tratamento de Erros

A API retorna mensagens de erro detalhadas no seguinte formato:
````
{
"timestamp": "2023-10-01T12:00:00",
"status": 400,
"error": "Bad Request",
"message": "O nome do produto é obrigatório.",
"path": "/api/products"
}
````

## 🧪 Testando a API
Você pode testar a API utilizando ferramentas como Postman, Insomia, NativeRest ou o bom e velho cURL.

### Exemplo com cURL
1. Criar Produto:
````
curl -X POST http://localhost:8080/api/products \
-H "Content-Type: application/json" \
-d '{
"name": "Produto Exemplo",
"description": "Descrição do produto",
"price": 100.0,
"stockQuantity": 10,
"category": "Eletrônicos"
}'
````

2. Listar Produtos:
````
curl -X GET http://localhost:8080/api/products
````

3. Buscar Produto por ID:
````
curl -X GET http://localhost:8080/api/products/1
````

4. Atualizar Produto:
````
curl -X PUT http://localhost:8080/api/products/1 \
-H "Content-Type: application/json" \
-d '{
"name": "Produto Atualizado",
"description": "Nova descrição",
"price": 150.0,
"stockQuantity": 5,
"category": "Roupas"
}'
````