package com.ing.mortgage.validator.specification;

public interface Specification<T> {
    boolean isSatisfiedBy(T item);
}
