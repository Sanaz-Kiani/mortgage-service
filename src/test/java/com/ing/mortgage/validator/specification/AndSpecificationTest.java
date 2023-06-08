package com.ing.mortgage.validator.specification;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AndSpecificationTest {

    @Test
    void isSatisfiedBy_BothSpecificationsSatisfied_ReturnsTrue() {
        // When
        Specification<Integer> specification1 = number -> number > 0;
        Specification<Integer> specification2 = number -> number % 2 == 0;

        Specification<Integer> andSpecification = new AndSpecification<>(specification1, specification2);

        // Having
        boolean result = andSpecification.isSatisfiedBy(6);

        // Assert
        assertTrue(result);
    }

    @Test
    void isSatisfiedBy_OneSpecificationNotSatisfied_ReturnsFalse() {
        // When
        Specification<Integer> specification1 = number -> number > 0;
        Specification<Integer> specification2 = number -> number % 2 == 0;

        Specification<Integer> andSpecification = new AndSpecification<>(specification1, specification2);

        // Having
        boolean result = andSpecification.isSatisfiedBy(-3);

        // Assert
        assertFalse(result);
    }

    @Test
    void isSatisfiedBy_BothSpecificationsNotSatisfied_ReturnsFalse() {
        // When
        Specification<Integer> specification1 = number -> number > 0;
        Specification<Integer> specification2 = number -> number % 2 == 0;

        Specification<Integer> andSpecification = new AndSpecification<>(specification1, specification2);

        // Having
        boolean result = andSpecification.isSatisfiedBy(-5);

        // Assert
        assertFalse(result);
    }
}
