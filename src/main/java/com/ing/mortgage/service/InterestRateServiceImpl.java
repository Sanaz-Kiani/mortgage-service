package com.ing.mortgage.service;

import com.ing.mortgage.domain.MortgageRate;
import com.ing.mortgage.repository.MortgageRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterestRateServiceImpl implements InterestRateService {
    private final MortgageRateRepository mortgageRateRepository;

    public List<Double> getAllInterestRates() {
        return mortgageRateRepository.findAll()
                .stream().map(MortgageRate::getInterestRate).toList();
    }


}
