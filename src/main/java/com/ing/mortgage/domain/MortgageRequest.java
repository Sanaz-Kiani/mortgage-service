package com.ing.mortgage.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class MortgageRequest {
    private BigDecimal loanValue;
    private BigDecimal income;
    private BigDecimal homeValue;
    private int maturityPeriod;


}
