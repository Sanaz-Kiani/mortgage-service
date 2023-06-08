package com.ing.mortgage.service;

import com.ing.mortgage.domain.MortgageRequest;

public interface MortgageFeasibilityService {
    boolean validateMortgage(MortgageRequest requestDTO);
}
