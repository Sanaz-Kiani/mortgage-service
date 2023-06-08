package com.ing.mortgage.validator.specification;

import com.ing.mortgage.domain.MortgageRequest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class MortgageIncomeSpecificationTest {

    @Test
    void isSatisfiedBy_LoanValueLessThanOrEqualToMaxMortgageAmount_ReturnsTrue() {
        // When
        BigDecimal loanValue = BigDecimal.valueOf(200000);
        BigDecimal income = BigDecimal.valueOf(60000); // Max income of 4 times the loan value
        MortgageRequest request = MortgageRequest.builder()
                .loanValue(loanValue)
                .income(income)
                .build();

        MortgageIncomeSpecification specification = new MortgageIncomeSpecification();

        // Having
        boolean result = specification.isSatisfiedBy(request);

        // Assert
        assertTrue(result);
    }

    @Test
    void isSatisfiedBy_LoanValueGreaterThanMaxMortgageAmount_ReturnsFalse() {
        // When
        BigDecimal loanValue = BigDecimal.valueOf(10000);
        BigDecimal income = BigDecimal.valueOf(2000);
        MortgageRequest request = MortgageRequest.builder()
                .loanValue(loanValue)
                .income(income)
                .build();

        MortgageIncomeSpecification specification = new MortgageIncomeSpecification();

        // Having
        boolean result = specification.isSatisfiedBy(request);

        // Assert
        assertFalse(result);
    }
}
