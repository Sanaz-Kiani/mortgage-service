package com.ing.mortgage.service;

import com.ing.mortgage.domain.MortgageRequest;

import java.math.BigDecimal;

public interface MortgageCalculatorService {
    BigDecimal calculateMonthlyCost(MortgageRequest request);


}
