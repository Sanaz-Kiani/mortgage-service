package com.ing.mortgage.service;

import com.ing.mortgage.controller.dto.MortgageCheckRequestDTO;
import com.ing.mortgage.controller.dto.MortgageCheckResponseDTO;
import com.ing.mortgage.exception.NotValidInterestRateException;
import com.ing.mortgage.repository.MortgageRateRepository;
import com.ing.mortgage.util.MortgageCalculator;
import com.ing.mortgage.validator.specification.AndSpecification;
import com.ing.mortgage.validator.specification.MortgageHomeValueSpecification;
import com.ing.mortgage.validator.specification.MortgageIncomeSpecification;
import com.ing.mortgage.validator.specification.Specification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class MortgageCheckServiceImpl implements MortgageCheckService {
    private final MortgageRateRepository mortgageRateRepository;
    Specification<MortgageCheckRequestDTO> mortgageSpecification;

    private boolean validate(MortgageCheckRequestDTO request) {
        mortgageSpecification = new AndSpecification<>(new MortgageIncomeSpecification(), new MortgageHomeValueSpecification());
        return mortgageSpecification.isSatisfiedBy(request);
    }

    public MortgageCheckResponseDTO performMortgageCheck(MortgageCheckRequestDTO requestDTO) {
        boolean feasible = validate(requestDTO);
        Double interestRate = mortgageRateRepository.findInterestRateByMaturityPeriod(requestDTO.getMaturityPeriod())
                .orElseThrow(() -> new NotValidInterestRateException("Interest rate not found for maturity period " + requestDTO.getMaturityPeriod()));
        BigDecimal monthlyCosts = MortgageCalculator.calculateMonthlyCost(requestDTO.getLoanValue(), interestRate, requestDTO.getMaturityPeriod());
        return MortgageCheckResponseDTO.builder()
                .feasible(feasible)
                .monthlyCosts(monthlyCosts).build();
    }
}
