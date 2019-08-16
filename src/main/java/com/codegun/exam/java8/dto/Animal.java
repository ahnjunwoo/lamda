package com.codegun.exam.java8.dto;

import lombok.Data;

@Data
public class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;

    }
}
