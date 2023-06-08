package com.ing.mortgage.service;

import com.ing.mortgage.domain.MortgageRequest;
import com.ing.mortgage.exception.NotValidInterestRateException;
import com.ing.mortgage.repository.MortgageRateRepository;
import com.ing.mortgage.util.MortgageCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class MortgageCalculatorServiceImpl implements MortgageCalculatorService {

    private final MortgageRateRepository mortgageRateRepository;

    public BigDecimal calculateMonthlyCost(MortgageRequest request) {
        Double interestRate = mortgageRateRepository.findInterestRateByMaturityPeriod(request.getMaturityPeriod())
                .orElseThrow(() -> new NotValidInterestRateException(
                        "Interest rate not found for maturity period " + request.getMaturityPeriod()));

        return MortgageCalculator.calculateMonthlyCost(request.getLoanValue(), interestRate, request.getMaturityPeriod());
    }
}
