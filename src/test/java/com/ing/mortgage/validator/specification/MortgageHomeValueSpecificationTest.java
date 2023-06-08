package com.ing.mortgage.validator.specification;

import com.ing.mortgage.controller.dto.MortgageCheckRequestDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MortgageHomeValueSpecificationTest {

    @Test
    void isSatisfiedBy_LoanValueLessThanOrEqualToHomeValue_ReturnsTrue() {
        // When
        MortgageCheckRequestDTO request = MortgageCheckRequestDTO.builder().
                loanValue(BigDecimal.valueOf(200000))
                .homeValue(BigDecimal.valueOf(300000))
                .build();
        MortgageHomeValueSpecification specification = new MortgageHomeValueSpecification();
        // Having
        boolean result = specification.isSatisfiedBy(request);
        // Assert
        assertTrue(result);
    }

    @Test
    void isSatisfiedBy_LoanValueGreaterThanHomeValue_ReturnsFalse() {
        // When
        MortgageCheckRequestDTO request = MortgageCheckRequestDTO.builder().loanValue(BigDecimal.valueOf(300000)).homeValue(BigDecimal.valueOf(200000)).build();

        MortgageHomeValueSpecification specification = new MortgageHomeValueSpecification();

        // Having
        boolean result = specification.isSatisfiedBy(request);

        // Assert
        assertFalse(result);
    }
}
