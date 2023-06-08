package com.ing.mortgage.validator.specification;

import com.ing.mortgage.controller.dto.MortgageCheckRequestDTO;
import com.ing.mortgage.controller.dto.MortgageCheckResponseDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * A specification that checks a mortgage does not exceed 4 times the income
 * It implements the Specification interface for MortgageRequest.
 */
@Component
public class MortgageIncomeSpecification implements Specification<MortgageCheckRequestDTO> {
    private static final int MAX_INCOME_MULTIPLIER = 4;

    /**
     * Checks whether the loan value is less than or equal to  4 times the income.
     *
     * @param request the mortgage check request containing loan value and income
     * @return true if the loan value is less than or equal to the mortgage amount based on the income, false otherwise
     */
    @Override
    public boolean isSatisfiedBy(MortgageCheckRequestDTO request) {
        BigDecimal mortgageAmount = request.getIncome().multiply(BigDecimal.valueOf(MAX_INCOME_MULTIPLIER));
        return request.getLoanValue().compareTo(mortgageAmount) <= 0;
    }
}

