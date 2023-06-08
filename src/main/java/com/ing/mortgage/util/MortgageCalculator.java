package com.ing.mortgage.util;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * Utility class for calculating the monthly cost of a mortgage loan.
 */
public class MortgageCalculator {

    /**
     * Calculates the monthly cost of a mortgage loan based on the loan amount, interest rate, and maturity period.
     *
     * @param loanAmount     the loan amount
     * @param interestRate   the interest rate
     * @param maturityPeriod the maturity period in months
     * @return the calculated monthly cost
     * @throws IllegalArgumentException if the maturity period is zero
     */
    public static BigDecimal calculateMonthlyCost(@NotNull BigDecimal loanAmount, double interestRate, int maturityPeriod) {
        if (maturityPeriod == 0) {
            throw new IllegalArgumentException("Maturity period cannot be 0");
        }
        // Sample formula for calculating monthly cost
        return loanAmount.multiply(BigDecimal.valueOf(interestRate)).divide(BigDecimal.valueOf(maturityPeriod));
    }
}

