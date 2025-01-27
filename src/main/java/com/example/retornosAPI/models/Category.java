package com.example.retornosAPI.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum Category {

    ELETRONICOS("Eletrônicos"),
    ROUPAS("Roupas"),
    ALIMENTOS("Alimentos");

    private final String displayName;
    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @JsonCreator
    public static Category fromValue(String value) {
        return Arrays.stream(Category.values())
                .filter(category -> category.name().equalsIgnoreCase(value) || category.getDisplayName().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Categoria inválida: " + value));
    }

    @JsonValue
    public String toValue() {
        return name();
    }
}

