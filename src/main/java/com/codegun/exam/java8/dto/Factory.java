package com.codegun.exam.java8.dto;

@FunctionalInterface
public interface Factory<T> {
    T create();
}
