package com.example.retornosAPI.exception;

/**
 * Exceção personalizada para indicar que um Produto não foi encontrado.
 */
public class ProductNotFoundException extends RuntimeException {

    /**
     * Construtor que aceita uma mensagem de erro.
     *
     * @param message Mensagem detalhada sobre a exceção.
     */
    public ProductNotFoundException(String message) {
        super(message);
    }
}