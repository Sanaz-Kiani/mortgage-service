package com.ing.mortgage.repository;

import com.ing.mortgage.domain.MortgageRate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MortgageRateInMemoryRepository implements MortgageRateRepository {
    Map<Integer, MortgageRate> mortgageRateMap = new HashMap<>();

    @Override
    public void saveAll(List<MortgageRate> mortgageRates) {
        mortgageRateMap.putAll(mortgageRates.stream()
                .collect(Collectors.toMap(MortgageRate::getMaturityPeriod, rate -> rate)));
    }

    @Override
    public List<MortgageRate> findAll() {
        return mortgageRateMap.values().stream().toList();
    }

    @Override
    public Optional<Double> findInterestRateByMaturityPeriod(int maturityPeriod) {
        return Optional.ofNullable(mortgageRateMap.get(maturityPeriod))
                .map(MortgageRate::getInterestRate);
    }
}

