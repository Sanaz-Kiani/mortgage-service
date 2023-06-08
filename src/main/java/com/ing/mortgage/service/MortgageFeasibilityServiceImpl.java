package com.ing.mortgage.service;

import com.ing.mortgage.domain.MortgageRequest;
import com.ing.mortgage.validator.specification.AndSpecification;
import com.ing.mortgage.validator.specification.MortgageHomeValueSpecification;
import com.ing.mortgage.validator.specification.MortgageIncomeSpecification;
import com.ing.mortgage.validator.specification.Specification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MortgageFeasibilityServiceImpl implements MortgageFeasibilityService {
    private Specification<MortgageRequest> mortgageSpecification;

    @Override
    public boolean validateMortgage(MortgageRequest request) {
        mortgageSpecification = new AndSpecification<>(new MortgageIncomeSpecification(), new MortgageHomeValueSpecification());
        return mortgageSpecification.isSatisfiedBy(request);
    }


}
