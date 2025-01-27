package com.example.retornosAPI.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum Category {

    ELETRONICOS("Eletrônicos"),
    // Define um valor do enum chamado ELETRONICOS, associado ao displayName "Eletrônicos".

    ROUPAS("Roupas"),
    // Define um valor do enum chamado ROUPAS, associado ao displayName "Roupas".

    ALIMENTOS("Alimentos");
    // Define um valor do enum chamado ALIMENTOS, associado ao displayName "Alimentos".

    private final String displayName;
    // Declara um campo privado e final chamado displayName, que armazena o nome legível para humanos da categoria.

    Category(String displayName) {
        // Construtor do enum, usado para inicializar o campo displayName.
        this.displayName = displayName;
        // Atribui o valor passado no construtor ao campo displayName.
    }

    public String getDisplayName() {
        // Metodo público que retorna o valor do campo displayName.
        return displayName;
        // Retorna o nome legível para humanos da categoria.
    }

    @JsonCreator
    // Anotação que indica que o metodo a seguir será usado para desserializar JSON em um valor do enum.

    public static Category fromValue(String value) {
        // Metodo estático que converte uma string em um valor do enum Category.
        return Arrays.stream(Category.values())
                // Cria um stream com todos os valores do enum (ELETRONICOS, ROUPAS, ALIMENTOS).
                .filter(category -> category.name().equalsIgnoreCase(value) || category.getDisplayName().equalsIgnoreCase(value))
                // Filtra os valores do enum para encontrar um que corresponda ao nome do enum (name()) ou ao displayName, ignorando maiúsculas e minúsculas.
                .findFirst()
                // Retorna o primeiro valor que corresponde ao filtro.
                .orElseThrow(() -> new IllegalArgumentException("Categoria inválida: " + value));
        // Lança uma exceção se nenhum valor correspondente for encontrado.
    }

    @JsonValue
    // Anotação que indica que o metodo a seguir será usado para serializar o enum em JSON.

    public String toValue() {
        // Metodo que define como o enum será representado no JSON de saída.
        return name();
        // Retorna o nome do enum (ex: "ELETRONICOS") como a representação no JSON.
    }
}

