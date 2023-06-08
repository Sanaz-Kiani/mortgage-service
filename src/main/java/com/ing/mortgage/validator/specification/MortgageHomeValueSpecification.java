package com.ing.mortgage.validator.specification;

import com.ing.mortgage.controller.dto.MortgageCheckRequestDTO;
import org.springframework.stereotype.Component;

/**
 * A specification that checks whether the loan value is less than or equal to the home value.
 * It implements the Specification interface for MortgageRequest.
 */
@Component
public class MortgageHomeValueSpecification implements Specification<MortgageCheckRequestDTO> {

    /**
     * Checks whether the loan value is less than or equal to the home value of the mortgage check request.
     *
     * @param request the mortgage check request containing loan value and home value
     * @return true if the loan value is less than or equal to the home value, false otherwise
     */
    @Override
    public boolean isSatisfiedBy(MortgageCheckRequestDTO request) {
        return request.getLoanValue().compareTo(request.getHomeValue()) <= 0;
    }
}