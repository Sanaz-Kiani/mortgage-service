package com.ing.mortgage.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Represents a mortgage rate.
 *
 * <p>
 * The {@code MortgageRate} class holds information about the maturity period,
 * interest rate, and the last update time of a mortgage rate.
 * </p>
 */
@Data
@AllArgsConstructor
@Builder
public class MortgageRate {
    private int maturityPeriod;
    private Double interestRate;
    private LocalDateTime lastUpdate;
}
