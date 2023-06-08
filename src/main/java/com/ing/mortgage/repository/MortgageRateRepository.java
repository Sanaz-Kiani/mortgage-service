package com.ing.mortgage.repository;

import com.ing.mortgage.domain.MortgageRate;

import java.util.List;
import java.util.Optional;

public interface MortgageRateRepository {
     void saveAll(List<MortgageRate> mortgageRates);

     List<MortgageRate> findAll();

     Optional<Double> findInterestRateByMaturityPeriod(int maturityPeriod);
}
